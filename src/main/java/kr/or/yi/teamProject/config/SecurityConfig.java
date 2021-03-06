package kr.or.yi.teamProject.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import kr.or.yi.teamProject.common.enums.CustomOAuth2Provider;
import kr.or.yi.teamProject.security.handler.CustomAuthenticationFailureHandler;
import kr.or.yi.teamProject.security.handler.CustomAuthenticationSuccessHandler;
import kr.or.yi.teamProject.security.service.CustomUserDetailsService;
import kr.or.yi.teamProject.security.service.OAuth2CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Configuration
//????????? MVC??? ????????? ???????????? ??????
@EnableWebSecurity
//????????? ??? properties ??????
@EncryptablePropertySource(name = "securityProperties", value = "classpath:/application.properties")
// @PreAuthorize @PostAuthorize @Secured ?????? ??????
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static List<String> clients = Arrays.asList("google", "kakao");

    private final OAuth2CustomUserDetailsService oAuth2CustomUserDetailsService;

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    //?????? ?????? ??????
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/manage").hasAnyRole("MANAGER", "ADMIN")
                .antMatchers("/order").hasRole("USER")
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated(); // ??? ????????? ?????? ??? ???????????? ?????? ??????

        //?????? ?????????
        http
                .oauth2Login().loginPage("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .userInfoEndpoint()
                .userService(oAuth2CustomUserDetailsService);

        //?????? ?????????
        http
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .permitAll();

        //????????????
        http
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .deleteCookies("remember-me", "JSESSION_ID");

        //?????? ?????????
        http
                .rememberMe()
                .key("yiTeamProject")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .authenticationSuccessHandler(successHandler());
                //.userDetailsService(customUserDetailsService);

        //CORS ??????
        http
                .headers()
                .frameOptions()
                .sameOrigin();

        //?????? ?????????
//        http
//                .exceptionHandling()
//                .authenticationEntryPoint( new AuthenticationEntryPoint() {//?????? ?????? ??????(??? ?????????) ????????? ??????
//
//                    @Override
//                    public void commence(HttpServletRequest request, HttpServletResponse response,
//                                         AuthenticationException authException) throws IOException, ServletException {
//                        log.info(authException.getMessage());
//                        request.getRequestDispatcher("/login").forward(request, response);
//                    }
//                })
//                .accessDeniedHandler( new AccessDeniedHandler() {// ????????? ????????? ?????? ???????????? ??????
//
//                    @Override
//                    public void handle(HttpServletRequest request, HttpServletResponse response,
//                                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                        log.info(accessDeniedException.getMessage());
//                        request.getRequestDispatcher("/access-denied").forward(request, response);
//                    }
//                });

    }



    // ?????? ?????? ???
    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        return  new CustomAuthenticationSuccessHandler(passwordEncoder());
    }

    // ?????? ?????? ???
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // userDetailsService ??? ???????????? ?????? ????????? ???????????? ??????(?????? ?????????)
        auth.userDetailsService(customUserDetailsService);

    }




    private static final String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";

    @Resource
    private Environment env;

    //OAuth2 ?????? ??????
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
        String[] scopeArr = scope.split(",");


        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).scope(scopeArr).build();
        }
        if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).scope(scopeArr).build();
        }
        if (client.equals("kakao")) {
            return CustomOAuth2Provider.KAKAO.getBuilder(client)
                    .clientId(clientId).clientSecret(clientSecret).build();
        }

        return null;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }


    // ???????????? ?????????
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    //?????? ????????? ?????? ????????????
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

}


