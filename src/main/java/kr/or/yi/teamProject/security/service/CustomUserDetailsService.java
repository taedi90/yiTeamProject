package kr.or.yi.teamProject.security.service;

import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 일반 로그인 용 커스텀 UserDetailsService
 *
 * - 비밀번호 확인은 CustomUserDetailsService 이후 단계에서 throw 되는 것으로 보임
 * @author taedi
 */

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Load User By Username : " + username);

        Member member = memberMapper.selectMember(
                Member.builder().username(username).build()
        );

        if(member == null) {
            throw new InternalAuthenticationServiceException("아이디를 다시 확인해 주세요.");
        }

        // 소셜 가입자 로그인 거부
        if(member.isSocial() == true) {
            throw new InternalAuthenticationServiceException(username + "은 소셜 가입자 입니다.");
        }

        // 이메일 미인증
        if(member.isEmailConfirm() == false) {
            throw new LockedException("이메일 인증을 완료해주세요.<br><a href=\"email-confirm\">인증 메일 재발송</a>");
        }

        // 탈퇴
        if(member.getWithdrawalDate() != null) {
            throw new DisabledException("이미 탈퇴한 계정입니다.");
        }


        return new CustomUser(member) ;
    }
}
