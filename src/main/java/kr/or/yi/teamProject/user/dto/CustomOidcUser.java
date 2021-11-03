package kr.or.yi.teamProject.user.dto;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.stream.Collectors;

@Getter
public class CustomOidcUser extends DefaultOidcUser {

    private Member member;

    public CustomOidcUser(Member dto, OidcUser oidcUser){
        super(
                dto.getAuthority().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority().toString())).collect(Collectors.toList()),
                oidcUser.getIdToken(),
                oidcUser.getUserInfo()
        );
        this.member = dto;
    }

}
