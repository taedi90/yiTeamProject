package kr.or.yi.teamProject.user.dto;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

@Getter
public class CustomUserDetails extends User {

    private Member member;

    public CustomUserDetails(Member dto){
        super(dto.getUsername(),
                dto.getPassword(),
                dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList()));
        this.member = dto;
    }

}
