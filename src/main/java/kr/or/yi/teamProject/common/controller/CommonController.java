package kr.or.yi.teamProject.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 공통 부분 컨트롤러
 *
 * @author taedi
 */

@Controller
public class CommonController {

    //요청 거부 페이지
    @GetMapping("/access-denied")
    public String accessDenied(Authentication auth, Model model) {
        model.addAttribute("msg", "Access Denied");

        return "error/access-denied";
    }
}

