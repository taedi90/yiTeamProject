package kr.or.yi.teamProject.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import kr.or.yi.teamProject.user.handler.CustomAuthenticationFailureHandler;
import kr.or.yi.teamProject.user.handler.CustomAuthenticationSuccessHandler;
import kr.or.yi.teamProject.user.service.CustomUserDetailsService;
import kr.or.yi.teamProject.user.service.OAuth2CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
//스프링 MVC와 스프링 시큐리티 결합
@EnableWebSecurity
//암호화 된 properties 사용
@EncryptablePropertySource(name = "securityProperties", value = "classpath:/application.properties")
// @PreAuthorize @PostAuthorize @Secured 사용 설정
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static List<String> clients = Arrays.asList("google", "facebook");

    private final OAuth2CustomUserDetailsService oAuth2CustomUserDetailsService;

    //접근 제한 처리
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated(); // 그 외에는 인증 된 사용자만 접근 가능


        http
                .oauth2Login().loginPage("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .userInfoEndpoint()
                .userService(oAuth2CustomUserDetailsService);


        http
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .permitAll();

        http
                .exceptionHandling()
                .authenticationEntryPoint( new AuthenticationEntryPoint() {// 인증 되지 않은 유저의 요청

                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException authException) throws IOException, ServletException {
                        response.sendRedirect("/authenticationEntryPoint");
                        log.info(authException.getMessage());
                    }
                })
                .accessDeniedHandler( new AccessDeniedHandler() {// 액세스 권한

                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response,
                                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.sendRedirect("/access-denied");
                    }
                });

        http
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me", "JSESSION_ID");
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    // 로그인 성공 시
    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        return  new CustomAuthenticationSuccessHandler(passwordEncoder());
    }


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // userDetailsService 를 사용자가 직접 정의한 클래스로 대체
        auth.userDetailsService(customUserDetailsService);

    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private static final String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";

    @Resource
    private Environment env;

    private ClientRegistration getRegistration(String client) {
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".client-secret");

        String scope = env.getProperty(
                CLIENT_PROPERTY_KEY + client + ".scope");


        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
//                    .clientId(clientId).clientSecret(clientSecret).build();
                    .clientId(clientId).clientSecret(clientSecret).scope("profile", "email").build();
        }
        if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).scope(scope).build();
        }
        return null;
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }


    // 패스워드 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

}


