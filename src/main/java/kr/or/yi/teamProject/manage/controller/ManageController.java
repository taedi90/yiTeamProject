package kr.or.yi.teamProject.manage.controller;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.service.ItemService;
import kr.or.yi.teamProject.security.dto.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 관리자 페이지 관련 컨트롤러
 *
 * @author taedi
 */

@Slf4j
@Controller
//manager 또는 admin 만 접근 가능
//@PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
//@PreAuthorize("hasRole('ROLE_MANAGER')")
@RequestMapping("/manage")
public class ManageController {
    //관리페이지 메인
    @GetMapping
    public String getManage() {
        return "manage/manage";
    }


    //상품
    @GetMapping("/product")
    public String getProduct(Model model) {

        model.addAttribute("url", "content/product/editor.jsp");

        return "manage/manage";
    }



    @Autowired
    ItemService itemService;

    //상품 관리 페이지
    @GetMapping(params = {"section=product","func=list"})
    public String getProductList(Pager pager, Model model) {

        log.info(pager.toString());

        pager = itemService.getInfoForPaging(pager);

        log.info(pager.toString());

        //CommonResult result = itemService.readItem();

        pager = itemService.readItemForManage(pager);

        model.addAttribute("url", "content/product/list.jsp");
        model.addAttribute("result", pager);

        return "manage/manage";
    }

    //상품 등록 페이지
    @GetMapping(params = {"section=product","func=create"})
    public String getProductCreate(Model model, Authentication authentication) {

        //관리자 정보 가져오기
        CustomUser user = (CustomUser) authentication.getPrincipal();

        Item item = Item.builder()
                .publish(false)
                .member(user.getMember())
                .build();

        //신규생성
        CommonResult result = itemService.createItem(item);


        log.info(result.getObject().toString());

        if(result.isSuccess() == true) {
            model.addAttribute("result", result.getObject());
            model.addAttribute("url", "content/product/editor.jsp");
        }

        return "manage/manage";
    }

    //상품 상세 페이지
    @GetMapping(params = {"section=product","func=detail"})
    public String getProductDetail(Model model, Authentication authentication) {
        return "manage/manage";
    }

    //상품 수정 페이지
    @GetMapping(params = {"section=product","func=edit"})
    public String getProductEdit(Model model, Authentication authentication) {
        return "manage/manage";
    }
}
