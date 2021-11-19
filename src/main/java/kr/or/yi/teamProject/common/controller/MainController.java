package kr.or.yi.teamProject.common.controller;

import kr.or.yi.teamProject.product.dto.ItemPager;
import kr.or.yi.teamProject.product.service.ItemService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Setter(onMethod_ = @Autowired)
    ItemService itemService;

    //접근권한 부여
    //@PreAuthorize("hasRole('USER')")
    //@Secured("ROLE_MANAGER")
    @GetMapping({"/", "/main"})
    public String getMainPage(ItemPager pager, Model model){

        //전체 페이지, 섹션별 페이지

        pager.setAmount(12);


        pager = itemService.getInfoForPaging(pager);

        pager = itemService.readItemForMain(pager);

        model.addAttribute("result", pager);

        return "main";

    }

}
