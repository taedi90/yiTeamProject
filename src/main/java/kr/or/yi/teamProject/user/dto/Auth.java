package kr.or.yi.teamProject.user.dto;

import kr.or.yi.teamProject.user.enums.Role;
import lombok.*;

/**
 * ROLE 전달 용 클래스
 *
 * @author taedi
 */
@Data // ToString, EqualsAndHashCode, Getter, Setter, RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private String username;
    private Role authority;
}


