package kr.or.yi.teamProject.user.dto;

import kr.or.yi.teamProject.manage.dto.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 쿠폰 발급 이력 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponHistory {
    private long issueNumber; //발급 번호
    private Coupon coupon; //쿠폰
    private Member member; //회원
    private Date regDate; //발급일자
    private Date expireDate; //만료일자
    private Date useDate; //사용일자
    private long useOrder; //사용주문
}
