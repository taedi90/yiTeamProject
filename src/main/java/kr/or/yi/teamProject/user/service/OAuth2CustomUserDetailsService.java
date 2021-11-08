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
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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

        // OAuth2 공급자 명
        String clientName = userRequest.getClientRegistration().getClientName().toLowerCase();


        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 널 값이면 반환하기
        if(oAuth2User == null){
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("1001"),
                    "정보 없음"
            );
        }

        // 이용자 정보 저장
        Map<String, Object> userInfo = oAuth2User.getAttributes();
        userInfo.forEach((k, v) -> log.info(k + " : " + v));


        // 이용자 정보 파싱
        String username = null;
        String name = null;
        String email = null;
        if(clientName.equals("kakao")){
            Map<String, Object> kakaoAccount = (Map<String, Object>)userInfo.get("kakao_account");
            Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

            if(kakaoAccount.get("email") == null){
                throw new InternalAuthenticationServiceException("이메일 정보를 공개하여야 가입이 가능합니다.");
            }

            username = clientName + "_" + kakaoAccount.get("email");
            name = (String) kakaoProfile.get("nickname");
            email = (String) kakaoAccount.get("email");

        }
        if(clientName.equals("google")){
            username = clientName + "_" + userInfo.get("email");
            name = (String) userInfo.get("name");
            email = (String) userInfo.get("email");
        }


        // 회원 여부 확인 및 신규 생성
        Member member = memberMapper.selectMember(
                Member.builder().username(username).build()
        );

        if (member == null){

            RandomStringCreateUtil rsUtil = new RandomStringCreateUtil();

            String password = new BCryptPasswordEncoder().encode(rsUtil.getSecureRand());

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

            member = memberMapper.selectMember(
                    Member.builder().username(username).build()
            );

        }


        return new CustomUser(member, userInfo);

    }

}
