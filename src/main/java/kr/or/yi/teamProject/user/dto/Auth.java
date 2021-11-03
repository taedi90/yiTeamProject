package kr.or.yi.teamProject.user.dto;

import kr.or.yi.teamProject.user.enums.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    private String username;
    private Role authority;
}
