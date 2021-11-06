package kr.or.yi.teamProject.user.service;

import kr.or.yi.teamProject.common.util.RandomStringCreateUtil;
import kr.or.yi.teamProject.user.dto.Auth;
import kr.or.yi.teamProject.user.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.enums.Role;
import kr.or.yi.teamProject.user.mapper.AuthMapper;
import kr.or.yi.teamProject.user.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * OAuth2 로그인 용 커스텀 UserDetailsService
 *
 * @author taedi
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2CustomUserDetailsService extends DefaultOAuth2UserService {

    private final MemberMapper memberMapper;

    private final AuthMapper authMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("----------------------------------");
        log.info("userRequest: " + userRequest);

        // OAuth2 공급자 명
        String clientName = userRequest.getClientRegistration().getClientName().toLowerCase();

        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 이용자 정보 저장
        Map<String, Object> userInfo = oAuth2User.getAttributes();

        String username = clientName + "_" + userInfo.get("email");



        // 널 값이면 반환하기
        if(oAuth2User == null){
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("1001"),
                    "정보 없음"
            );
        }

        Member member = memberMapper.selectMember(username);



        // 회원이 아니면 추가하기
        if (member == null){

            RandomStringCreateUtil rsUtil = new RandomStringCreateUtil();

            String name = (String) userInfo.get("name");
            String password = new BCryptPasswordEncoder().encode(rsUtil.getSecureRand());
            String email = (String) userInfo.get("email");

            member = Member.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .email(email)
                    .build();

            int res = memberMapper.insertSocialMember(member);
            log.info("회원 추가 - > " + res);

            // ROLE_USER 부여
            Auth auth = Auth.builder()
                    .username(username)
                    .authority(Role.ROLE_USER)
                    .build();

            authMapper.insertAuth(auth);

            member = memberMapper.selectMember(username);

        }


        return new CustomUser(member, userInfo);

    }

}
