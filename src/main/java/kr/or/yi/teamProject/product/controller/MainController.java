package kr.or.yi.teamProject.product.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    //접근권한 설정
    @PreAuthorize("hasRole('USER')")
    //@Secured("ROLE_ADMIN")
    @GetMapping({"/", "/main"})
    public String mainPage(){
        return "main";
    }
}
