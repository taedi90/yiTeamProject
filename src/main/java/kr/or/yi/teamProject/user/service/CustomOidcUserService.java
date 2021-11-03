package kr.or.yi.teamProject.user.service;


import kr.or.yi.teamProject.user.dto.Auth;
import kr.or.yi.teamProject.user.dto.CustomOidcUser;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.enums.Role;
import kr.or.yi.teamProject.user.mapper.AuthMapper;
import kr.or.yi.teamProject.user.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class CustomOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AuthMapper authMapper;

    final OidcUserService oidcUserService = new OidcUserService();
    
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

        OidcUser oidcUser = oidcUserService.loadUser(userRequest);

        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        log.info("----------------------------------");
        oidcUser.getAttributes().forEach((k,v) -> {log.info(k + " : " + v);});

        Map<String, Object> userInfo = oidcUser.getAttributes();

        String username = "oauth_" + (String) userInfo.get("email");



        // 널 값이면 반환하기
        if(oidcUser == null){
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("1"),
                    "정보 없음"
                    );
        }

        Member member = memberMapper.read(username);

        // 회원이 아니면 추가하기
        if (member == null){

            String name = (String) userInfo.get("name");
            String password = new BCryptPasswordEncoder().encode("test").toString();
            String email = (String) userInfo.get("email");

            member = Member.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .email(email)
                    .build();

            int res = memberMapper.insertSocialMember(member);
            log.info("회원 추가 - > " + res);

            Auth auth = Auth.builder()
                    .username(username)
                    .authority(Role.ROLE_USER)
                    .build();

            authMapper.insertAuth(auth);

            member = memberMapper.read(username);

        }

        return new CustomOidcUser(member, oidcUser);
    }
}
