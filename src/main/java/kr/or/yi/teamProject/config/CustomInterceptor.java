package kr.or.yi.teamProject.config;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.user.enums.RegisterResult;
import kr.or.yi.teamProject.user.enums.SendConfirmMailResult;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 컨트롤러 respond 이후 enum 초기화를 위한 인터셉터
 */
@Component
public class CustomInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //enum 초기화
        for (CommonResult cr : CommonResult.values()){
            cr.setObject(null);
        }
        for (RegisterResult rr : RegisterResult.values()) {
            rr.setCookie(null);
        }
        for (SendConfirmMailResult scmr : SendConfirmMailResult.values()) {
            scmr.setCookie(null);
        }

        super.afterCompletion(request, response, handler, ex);
    }
}
