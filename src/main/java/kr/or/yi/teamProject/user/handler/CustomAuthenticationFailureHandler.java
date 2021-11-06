package kr.or.yi.teamProject.user.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

/**
 * Security 인증 실패 핸들러
 *
 * @author taedi
 */
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        // log 생성
        log.info("=== 인증 실패 ===");
        log.info("에러 클래스 : " + e.getClass());
        log.info("에러 메세지 : " + e.getMessage());

        log.info("=== 입력 정보 ===");
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            log.info(param + " : " + request.getParameter(param));
        }

        // 오류 메세지 생성
        String errorMessage = e.getMessage();
        if (errorMessage == null) {
            errorMessage = "아이디 및 비밀번호를 확인해주세요!";
        }

        //String message = URLEncoder.encode(errorMessage, "UTF-8"); //url에 담을 경우 인코딩 필요


        // 리다이렉트
        request.getSession().setAttribute("loginErrorMessage", errorMessage);
        redirectStrategy.sendRedirect(request, response,"/login?error");
    }
}
