package kr.or.yi.teamProject.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
public class CustomOAuth2User extends User implements OAuth2User {

    private List<GrantedAuthority> authorities;
    private Map<String, Object> attribute;

    private Member member;


    public CustomOAuth2User(Member dto, Map<String, Object> attribute){
        super(dto.getUsername(),
                dto.getPassword(),
                dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList()));
        this.authorities = dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList());

        this.attribute = attribute;

        this.member = dto;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return this.attribute;

    }

    @Override
    public String getName() {
        return (String)attribute.get("name");
    }
}
