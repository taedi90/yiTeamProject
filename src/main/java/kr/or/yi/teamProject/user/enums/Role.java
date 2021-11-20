package kr.or.yi.teamProject.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ROLE 정의
 *
 * @author taedi
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum Role {
    ROLE_UNCERTIFIED(1,"미인증"),
    ROLE_USER(2,"회원"),
    ROLE_MANAGER(3,"매니저"),
    ROLE_ADMIN(4,"관리자");

    private final int grade;
    private final String title;

}
