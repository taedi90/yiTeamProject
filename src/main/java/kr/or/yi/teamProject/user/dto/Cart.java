package kr.or.yi.teamProject.user.dto;

import kr.or.yi.teamProject.product.dto.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 장바구니 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Member member; //회원
    private Option option; //옵션 + 상품 + 카테고리
    private int quantity; //수량
    private Date regDate; //등록일자
    private Date modDate; //수정일자
}
