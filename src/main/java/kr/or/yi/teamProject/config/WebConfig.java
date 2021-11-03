package kr.or.yi.teamProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import java.util.Properties;


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

    @Bean(name="customMappingExceptionResolver")
    public SimpleMappingExceptionResolver customMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();

        r.setDefaultErrorView("error/error");

        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");
        mappings.setProperty("DemoException", "demoError");

        r.setExceptionMappings(mappings);
        r.setDefaultErrorView("error/error");
        r.setExceptionAttribute("ex");
        return r;
    }

}
