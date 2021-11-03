package kr.or.yi.teamProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@EnableWebMvc
@ComponentScan(basePackages = {"kr.or.yi.teamProject"})
public class ServletConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry)  {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/views/");
        bean.setSuffix(".jsp");
        registry.viewResolver(bean);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
    }

    //파일 업로드
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        return resolver;
    }

    // 에러 처리
//    @Bean
//    public SimpleMappingExceptionResolver getExceptionResolver() {
//
//        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
//
//
//        // 지정되지 않은 예외에 대한 기본 에러페이지 입니다.
//
//        smer.setDefaultErrorView("error/error");
//
//        // 상태코드 맵핑이 없는 예외를 위한 기본 상태값 입니다.
//
//        smer.setDefaultStatusCode(200);
//
//        // 기본값이 "exception" 입니다. 예외 모돌 속성의 키값입니다. ${exception.message}
//
//        smer.setExceptionAttribute("exception");
//
//
//        return smer;
//
//    }

}
