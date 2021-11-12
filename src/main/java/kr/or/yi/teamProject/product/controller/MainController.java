package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    //접근권한 부여
    //@PreAuthorize("hasRole('USER')")
    //@Secured("ROLE_MANAGER")
    @GetMapping({"/", "/main"})
    public String getMainPage(Model model){

        CommonResult commonResult = mainService.getMainPage();

        //데이터 조회에 성공하면 메인 페이지 실행
        if(commonResult.isSuccess() == true) {
            model.addAttribute("items", commonResult.getObject());
            return "main";
        }


        return "error/error";

    }

}
