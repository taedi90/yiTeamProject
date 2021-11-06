package kr.or.yi.teamProject.user.controller;

import kr.or.yi.teamProject.user.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 회원 (로그인, 로그아웃, 회원가입) 관련 컨트롤러
 *
 * @author taedi
 */
@Slf4j
@Controller
public class MemberController {
    private static String authorizationRequestBaseUri
            = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
            = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    // 로그인 컨트롤러
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "user/login";
    }

    // 로그인 에러 시 처리 컨트롤러
    @GetMapping(value = "/login", params = "error")
    public String getLoginPage2(Model model, HttpServletRequest request) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        String message = (String) request.getSession().getAttribute("test");
        model.addAttribute("message", message);

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
