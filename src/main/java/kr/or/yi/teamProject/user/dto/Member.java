package kr.or.yi.teamProject.user.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * 회원 정보 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String username;
    private String password;
    private String name;
    private String email;
    private boolean emailConfirm;
    private String phone;
    private Date regDate;
    private Date withdrawalDate;
    private boolean social;
    private boolean tempPass;
    private boolean enabled;
    private List<Auth> authority;
}

