package kr.or.yi.teamProject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 개인정보 처리 방침 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivacyPolicy {
    private int policyNo;
    private String text;
    private Date regDate;
}
