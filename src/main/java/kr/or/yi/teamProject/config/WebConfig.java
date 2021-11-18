package kr.or.yi.teamProject.config;


import kr.or.yi.teamProject.common.util.ImageUtil;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;


public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected  Class<?>[] getRootConfigClasses() {
        return new Class[] {kr.or.yi.teamProject.config.RootConfig.class, kr.or.yi.teamProject.config.SecurityConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {kr.or.yi.teamProject.config.ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    //인코딩 필터
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //에러페이지 핸들링 - 404 에러를 DispatcherServlet 에서 처리하지 않고 throw 하도록 설정
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");

        //파일 업로드 (1mb * 20, 40mb, 20mb)
        MultipartConfigElement multipartConfig = new MultipartConfigElement(ImageUtil.UPLOAD_BASE_PATH, 20971520,41943040,20971520);
        registration.setMultipartConfig(multipartConfig);
    }

}
