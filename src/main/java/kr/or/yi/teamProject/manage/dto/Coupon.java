package kr.or.yi.teamProject.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 쿠폰 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private int couponNo; //쿠폰번호
    private String name; //쿠폰 명
    private int amount; //정액 할인
    private int minPrice; //제품 하한가
    private double percent; //정률 할인
    private int maxDiscount; //할인 상한가
    private int validity; //유효기간
}
