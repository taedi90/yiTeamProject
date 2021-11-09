package kr.or.yi.teamProject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 비회원 정보 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NonMember {
    private String nonUsername; //임시 아이디
    private String password; //비밀번호
    private String email; //이메일
    private String phone; //휴대폰
    private Date regDate; //등록일자
}
