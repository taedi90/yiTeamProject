package kr.or.yi.teamProject.manage.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.ItemPager;
import kr.or.yi.teamProject.product.service.ItemService;
import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.dto.MemberPager;
import kr.or.yi.teamProject.user.enums.Role;
import kr.or.yi.teamProject.user.service.MemberService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public String getManage(Model model) {

        model.addAttribute("url", "content/dashboard.jsp");
//        model.addAttribute("result", pager);

        return "manage/manage";
    }

    @Setter(onMethod_ = @Autowired)
    ItemService itemService;

    //상품 관리 페이지
    @GetMapping(params = {"section=product","func=list"})
    public String getProductList(ItemPager pager, Model model) {

        log.info(pager.toString());

        pager = itemService.getInfoForPaging(pager);

        log.info(pager.toString());

        pager = itemService.readItemForManage(pager);

        model.addAttribute("url", "content/product/product-list.jsp");
        model.addAttribute("result", pager);

        return "manage/manage";
    }

    //상품 등록 페이지
    @GetMapping(params = {"section=product","func=create"})
    public String getProductCreate(Model model, Authentication authentication) throws Exception {

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
            model.addAttribute("url", "content/product/product-editor.jsp");
        }

        return "manage/manage";
    }

    //상품 상세 페이지
    @GetMapping(params = {"section=product","func=detail"})
    public String getProductDetail(@RequestParam("itemNo") int itemNo, Model model) {


        CommonResult result = itemService.readItem(itemNo);

        model.addAttribute("result", result.getObject());
        model.addAttribute("url", "content/product/product-detail.jsp");

        return "manage/manage";
    }

    //상품 수정 페이지
    @GetMapping(params = {"section=product","func=edit"})
    public String getProductEdit(@RequestParam("itemNo") int itemNo, Model model, Authentication authentication) {

        //권한확인

        CommonResult result = itemService.readItem(itemNo);

        model.addAttribute("result", result.getObject());
        model.addAttribute("url", "content/product/product-editor.jsp");

        return "manage/manage";
    }

    @Setter(onMethod_ = @Autowired)
    MemberService memberService;

    //회원 관리 페이지
    @GetMapping(params = {"section=member","func=list"})
    public String getMemberList(MemberPager pager, Model model) {

        pager = memberService.selectMemberListForManage(pager);

        model.addAttribute("result", pager);
        model.addAttribute("url", "content/member/member-list.jsp");

        return "manage/manage";
    }

    //회원 상세 페이지
    @GetMapping(params = {"section=member","func=detail"})
    public String getMemberDetail(@RequestParam("username") String username, Model model) {

        Member member = Member.builder().username(username).build();

        member = memberService.selectMember(member);

        model.addAttribute("result", member);
        model.addAttribute("url", "content/member/member-detail.jsp");

        return "manage/manage";
    }

    //관리자 설정 페이지
    @Secured("ROLE_ADMIN")
    @GetMapping(params = {"section=admin","func=list"})
    public String getAdminList(MemberPager pager, Model model) {

        pager.setAuthority("ROLE_MANAGER");

        pager = memberService.selectMemberListForAdmin(pager);

        model.addAttribute("result", pager);
        model.addAttribute("url", "content/admin/admin-list.jsp");

        return "manage/manage";
    }

}
