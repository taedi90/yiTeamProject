package kr.or.yi.teamProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;
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
        registry.addResourceHandler("/upload/**").addResourceLocations("/resources/upload/");
        registry.addResourceHandler("/se2/**").addResourceLocations("/resources/se2/");
    }

    //파일 업로드
    @Bean
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        return resolver;
    }

    //오류 페이지 처리
    @Bean
    HandlerExceptionResolver customExceptionResolver () {
        CustomSimpleMappingExceptionResolver resolver = new CustomSimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        // Mapping Spring internal error NoHandlerFoundException to a view name
        mappings.setProperty(NoHandlerFoundException.class.getName(), "/error/error"); //404
        mappings.setProperty(NullPointerException.class.getName(), "/error/error"); //500
        mappings.setProperty(ClassNotFoundException.class.getName(), "/error/error"); //500
        mappings.setProperty(AccessDeniedException.class.getName(), "/error/error"); //Security 권한 인가 예외
        mappings.setProperty(Exception.class.getName(), "/error/error"); //그 외
        resolver.setExceptionMappings(mappings);
        // Set specific HTTP codes
        resolver.addStatusCode("404", HttpStatus.NOT_FOUND.value());
        resolver.addStatusCode("500", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resolver.setDefaultErrorView("/error/error");
        resolver.setDefaultStatusCode(200);
        // This resolver will be processed before the default ones
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        resolver.setExceptionAttribute("exception");
        return resolver;
    }

    //커스텀 인터셉터
    @Autowired
    CustomInterceptor customInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor).excludePathPatterns("/resources/**");
    }

}
