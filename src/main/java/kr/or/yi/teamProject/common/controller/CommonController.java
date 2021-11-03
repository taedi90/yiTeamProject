package kr.or.yi.teamProject.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model) {
        System.out.println("access Denied : " + auth);

        model.addAttribute("msg", "Access Denied");
    }
}

