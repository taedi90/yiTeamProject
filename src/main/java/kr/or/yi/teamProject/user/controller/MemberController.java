package kr.or.yi.teamProject.user.controller;

import kr.or.yi.teamProject.user.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 회원 (로그인, 로그아웃, 회원가입) 관련 컨트롤러
 *
 * @author taedi
 */
@Slf4j
@Controller
public class MemberController {

    // 로그인 컨트롤러
    @GetMapping("/login")
    public String getLoginPage(Model model, HttpServletRequest request) {

        if(request.isUserInRole("ROLE_USER")) {
            log.info("이미 로그인 했을 경우 메인 페이지로 이동");
            return "redirect:/";
        }

        //로그인 오류 표시
        String loginErrorMessage = (String) request.getSession().getAttribute("loginErrorMessage");
        if(loginErrorMessage != null) {
            model.addAttribute("loginErrorMessage", loginErrorMessage);
            request.getSession().removeAttribute("loginErrorMessage"); //세선에 값 비워주기
        }

        return "user/login";
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public String getRegister(Model model) {

        return "user/register";
    }

    // 회원가입 요청
    @PostMapping("/register")
    @ResponseBody
    public String postRegister(@RequestBody Member member, Model model) {
        log.info("----------------");
        log.info(member.toString());


        return "main";
    }


    @GetMapping("/test")
    public String test(){
        return "main2";
    }
}
