package kr.or.yi.teamProject.user.controller;

import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.enums.RegisterResult;
import kr.or.yi.teamProject.user.service.MemberService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Setter(onMethod_ =  {@Autowired})
    MemberService memberService;

    // 로그인 컨트롤러
    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request) {

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
    public String getRegister() {

        return "user/register";
    }

    // 회원가입 요청
    @PostMapping("/register")
    @ResponseBody
    public RegisterResult postRegister(@RequestBody Member member) {
        log.info("client로 부터 받은 입력 값 : " + member.toString());

        RegisterResult result = memberService.insertNormalMember(member);

        log.info("가입 성공 여부 : " + String.valueOf(result.isSuccess()));
        log.info("상세 내용" + result.getComment());

        return result;
    }

}
