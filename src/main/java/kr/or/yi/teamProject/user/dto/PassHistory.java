package kr.or.yi.teamProject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 패스워드 이력 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassHistory {
    private Member member; //회원
    private String password; //비밀번호
    private Date regDate; //설정일자
}
