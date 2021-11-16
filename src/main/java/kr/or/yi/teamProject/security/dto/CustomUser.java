package kr.or.yi.teamProject.security.dto;

import kr.or.yi.teamProject.user.dto.Member;
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

/**
 * Security 용 Custom User 클래스
 *
 * - 일반 로그인 사용자 및 OAuth2 사용자 모두 사용
 * @author taedi
 */
@Getter
@Setter
@ToString
public class CustomUser extends User implements OAuth2User {

    // 공통
    private Member member;
    private List<GrantedAuthority> authorities;

    // OAuth2 용
    private Map<String, Object> attribute;

    // 일반 사용자용 생성자
    public CustomUser(Member dto){
        super(dto.getUsername(),
                dto.getPassword(),
                dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList()));
        this.authorities = dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList());
        this.member = dto;
        this.member.setPassword(null);
    }

    // OAuth2 사용자용 생성자
    public CustomUser(Member dto, Map<String, Object> attribute){
        super(dto.getUsername(),
                dto.getPassword(),
                dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList()));
        this.authorities = dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList());

        this.attribute = attribute;

        this.member = dto;
        this.member.setPassword(null);
    }

    // 아래는 OAuth2User impl을 위한 메서드
    @Override
    public Map<String, Object> getAttributes() {

        return this.attribute;

    }

    @Override
    public String getName() {
        return (String)attribute.get("name");
    }
}
