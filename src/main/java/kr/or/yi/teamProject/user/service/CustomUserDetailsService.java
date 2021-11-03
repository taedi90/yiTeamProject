package kr.or.yi.teamProject.user.service;

import kr.or.yi.teamProject.user.dto.CustomUserDetails;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("Load User By Username : " + username);

        Member member = memberMapper.read(username);

        if(member == null) {
            throw new UsernameNotFoundException(username + "is not found.");
        }



        return new CustomUserDetails(member) ;
    }
}
