package kr.or.yi.teamProject.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //접근권한 부여
    //@PreAuthorize("hasRole('USER')")
    //@Secured("ROLE_MANAGER")
    @GetMapping({"/", "/main"})
    public String mainPage(){
        return "main";
    }

}
