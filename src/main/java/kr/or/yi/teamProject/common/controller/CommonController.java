package kr.or.yi.teamProject.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 공통 부분 컨트롤러
 *
 * @author taedi
 */


@Controller
public class CommonController {
    @GetMapping("/access-denied")
    public String getAccessDenied(){
        return "error/access-denied";
    }

}

