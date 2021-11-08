package kr.or.yi.teamProject.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.Properties;

@Slf4j
@Configuration
//암호화 된 properties 사용
@EncryptablePropertySource(name = "mailSenderProperties", value = "classpath:/mail-sender.properties")
public class MailConfig {

    @Resource
    private Environment env;


    @Bean(name="mailSender")
    public JavaMailSender getJavaMailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.debug", true);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("mailSender.host"));
        mailSender.setPort(465);
        mailSender.setUsername(env.getProperty("mailSender.username"));
        mailSender.setPassword(env.getProperty("mailSender.password"));
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setJavaMailProperties(properties);

        return mailSender;

    }
}
