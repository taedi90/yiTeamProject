package kr.or.yi.teamProject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 상품 옵션 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private long optionNo; //옵션번호
    private Item item; //상품정보 + 카테고리
    private String name; //옵션명
    private int optionPrice; //추가금액
    private int stock; //재고수량

}
