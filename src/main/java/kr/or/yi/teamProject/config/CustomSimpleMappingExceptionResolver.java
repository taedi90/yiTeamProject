package kr.or.yi.teamProject.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * 에러 페이지 처리용 리졸버
 *
 * 참고 : https://carolinafernandez.github.io/development/2019/03/25/Custom-Spring-error-with-Java-config
 * @author taedi
 */

public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    public CustomSimpleMappingExceptionResolver() {
        setWarnLogCategory(getClass().getName());
    }

    @Override
    public String buildLogMessage(Exception e, HttpServletRequest req) {
        return "MVC exception: " + e.getLocalizedMessage();
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {

        // Log exception
        ex.printStackTrace();
        String exceptionCause = ex.toString();
        String exceptionType = ex.getClass().getCanonicalName();

        // Get the ModelAndView to use
        ModelAndView mav = super.doResolveException(request, response, handler, ex);

        // Make more information available to the view - note that SimpleMappingExceptionResolver adds the exception already
        mav.addObject("url", request.getRequestURL());
        mav.addObject("timestamp", new Date());

        ArrayList<String> exceptions404 = new ArrayList<String>(
                Arrays.asList(
                        NoHandlerFoundException.class.getName()
                )
        );
        ArrayList<String> exceptions500 = new ArrayList<String>(
                Arrays.asList(
                        //InternalServerErrorException.class.getName(),
                        NullPointerException.class.getName()
                )
        );
        ArrayList<String> accessException = new ArrayList<String>(
                Arrays.asList(
                        AccessDeniedException.class.getName()
                )
        );

        String userExceptionDetail = ex.toString();
        String errorDescription = "";
        String errorTitle = "";

        if (exceptions404.contains(exceptionType)) {
            errorDescription = "페이지가 존재하지 않거나 일시적인 오류일 수 있습니다.";
            errorTitle = "페이지를 찾을 수 없습니다.";
            userExceptionDetail = String.format("The page %s cannot be found", request.getRequestURL());
            mav.setViewName("/error/error");
            mav.addObject("status", HttpStatus.NOT_FOUND.value());
        } else if (exceptions500.contains(exceptionType)) {
            errorDescription = "내부적 문제로 요청하신 내용을 처리할 수 없습니다.";
            errorTitle = "요청을 처리할 수 없습니다.";
            userExceptionDetail = "The current page refuses to load due to an internal error";
            mav.setViewName("/error/error");
            mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else if (accessException.contains(exceptionType)) {
            errorDescription = "요청하신 사항을 접근할 수 있는 권한이 없습니다.";
            errorTitle = "요청을 처리할 수 없습니다.";
            userExceptionDetail = String.format("You don't have a permission to access the page %s", request.getRequestURL());
            mav.setViewName("/error/error");
            mav.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else {
            errorDescription = "알 수 없는 이유로 요청하신 내용을 처리할 수 없습니다.";
            errorTitle = "사이트 오류";
            userExceptionDetail = "A generic error prevents from serving the page";
            mav.setViewName("/error/error");
            mav.addObject("status", response.getStatus());
        }

        Exception userException = new Exception(userExceptionDetail);
        mav.addObject("errorTitle", errorTitle);
        mav.addObject("errorDescription", errorDescription);
        mav.addObject("exception", userException);
        return mav;
    }
}