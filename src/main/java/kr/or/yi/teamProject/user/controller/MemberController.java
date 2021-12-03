package kr.or.yi.teamProject.user.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.dto.PrivacyAgreement;
import kr.or.yi.teamProject.user.dto.PrivacyPolicy;
import kr.or.yi.teamProject.user.enums.RegisterResult;
import kr.or.yi.teamProject.user.enums.SendConfirmMailResult;
import kr.or.yi.teamProject.user.mapper.MemberMapper;
import kr.or.yi.teamProject.user.mapper.PrivacyAgreementMapper;
import kr.or.yi.teamProject.user.mapper.PrivacyPolicyMapper;
import kr.or.yi.teamProject.user.service.MemberService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    StringEncryptor encryptor;

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

    @Setter(onMethod_ = @Autowired)
    PrivacyPolicyMapper privacyPolicyMapper;

    // 개인정보처리방침
    @GetMapping("policy")
    public String getPolicy(Model model) {
        PrivacyPolicy policy = privacyPolicyMapper.selectPrivacyPolicy();
        model.addAttribute("policy", policy);
        return "user/policy";
    }


    // 회원가입 페이지
    @GetMapping("/register")
    public String getRegister(@RequestParam("policyNo")String policyNo, Model model) {

        model.addAttribute("policyNo", policyNo);

        return "user/register";
    }

    @Setter(onMethod_ = @Autowired)
    PrivacyAgreementMapper privacyAgreementMapper;

    // 회원가입 요청
    @PostMapping("/register")
    @ResponseBody
    public RegisterResult postRegister(@RequestPart("member") Member member,
                                       @RequestPart("policyNo")String policyNo,
                                       HttpServletResponse response) {
        log.info("client로 부터 받은 입력 값 : " + member.toString());
        log.info("client로 부터 받은 입력 값 : " + policyNo);

        RegisterResult result = memberService.insertNormalMember(member);

        log.info("가입 성공 여부 : " + String.valueOf(result.isSuccess()));
        log.info("상세 내용" + result.getComment());

        //이메일 발송 성공 시
        if(result.isSuccess() == true){

            PrivacyAgreement privacyAgreement = PrivacyAgreement.builder()
                    .member(member)
                    .policyNo(Integer.parseInt(policyNo))
                    .build();
            privacyAgreementMapper.insertPrivacyAgreement(privacyAgreement);

            // 쿠키 생성
            response.addCookie(result.getCookie());
            result.setCookie(null);
            return result;
        }

        return result;
    }

    //이메일 확인 페이지
    @GetMapping("/email-confirm")
    public String getEmailConfirm(@RequestParam(value = "id", required = false)String username,
                                  @RequestParam(value = "confirm", required = false)String authKey,
                                  @CookieValue(value = "emailConfirm", required = false)Cookie emailConfirmCookie) {

        log.info("=== 이메일 확인 ===");
        log.info(username);
        log.info(authKey);

        //decrypt
        if(emailConfirmCookie != null){

            String cookieValue = encryptor.decrypt(emailConfirmCookie.getValue());

            log.info(cookieValue);

            if(cookieValue.equals(username + authKey)){
                //이메일 인증 완료 처리
                CommonResult result = memberService.updateMember(
                        Member.builder()
                                .username(username)
                                .emailConfirm(true)
                        .build()
                );

                if(result.isSuccess() == true){
                    return "/user/email-confirm-success";
                }
            }

        }

        //세션 유지 실패, 만료된 메일 등

        return "/user/email-confirm";
    }

    //이메일 확인 메일 발송
    @PostMapping("/email-confirm")
    @ResponseBody
    public SendConfirmMailResult postEmailConfirm(
            @RequestBody Member member,
            HttpServletResponse response) {

        SendConfirmMailResult result = memberService.sendConfirmMail(member);

        //이메일 발송 성공 시
        if(result.isSuccess() == true){
            // 쿠키 생성
            response.addCookie(result.getCookie());
            result.setCookie(null);
            return result;
        }

        return result;
    }

    //비 관리자 조회
    @PostMapping("/search-non-admin")
    @ResponseBody
    public List<Member> searchNonAdmin(
            @RequestBody Member member) {

        List<Member> list = memberService.selectNonManagerList(member.getUsername());

        return list;
    }

    @PostMapping("/check-user")
    @ResponseBody
    public CommonResult checkUser(HttpServletRequest request,
                                  @RequestBody(required = false) Map<String,Object> map) {

        // 이미 로그인 되어 있다면 리턴
        if(request.isUserInRole("ROLE_USER")) {
            return CommonResult.SUCCESS;
        }

        // 로그인 후 리다이렉트 희망 주소가 있다면 세션에 저장
        String targetUrl = (String) map.get("targetUrl");

        if(targetUrl != null){
            request.getSession().setAttribute("targetUrl", targetUrl);
        }

        return CommonResult.FAILURE;
    }

    // 정보변경


    // 회원탈퇴
    @PostMapping("/deactivate-member")
    @ResponseBody
    public CommonResult withDraw(Authentication authentication,
                                @RequestBody Map<String, String> map,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        Member member = user.getMember();

        member.setPassword(map.get("password"));

        CommonResult result = memberService.withDraw(member);

        if(result.isSuccess()) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return CommonResult.SUCCESS;
        }

        return CommonResult.FAILURE;
    }
}
