package kr.or.yi.teamProject.user.handler;

import kr.or.yi.teamProject.user.dto.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security 인증 완료 핸들러
 *
 * @author taedi
 */
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        CustomUser customUser = (CustomUser) authentication.getPrincipal();

        log.info("=== 로그인 정보 ===");
        log.info(customUser.toString());


        // 비밀번호 변경 필요 -> 다른 페이지 접속 불가 처리, 정보변경 페이지 리다이렉트
        if(customUser.getMember().isTempPass() == true) {
            // 미구현
        }


        // 리다이렉트
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest != null){ // 인증이 필요한 리소스에 접근하려다 로그인 화면으로 넘어간경우
            redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        }else{  // 직접 로그인 페이지로 이동해서 들어온경우 메인페이지로 리다이렉트
            redirectStrategy.sendRedirect(request, response, "/");
        }


    }
}
