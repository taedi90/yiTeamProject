package kr.or.yi.teamProject.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * Security 메세지 커스텀
 *
 * @author taedi
 */

@Configuration
public class SecurityMessageConfig {

    @Bean
    public MessageSource messageSource() {

        Locale.setDefault(Locale.KOREA); // 위치 한국으로 설정
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setDefaultEncoding("UTF-8"); // 인코딩 설정
        messageSource.setBasename("classpath:/security-message");
        messageSource.setCacheSeconds(60);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
