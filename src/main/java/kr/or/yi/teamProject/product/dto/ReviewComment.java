package kr.or.yi.teamProject.product.dto;

import kr.or.yi.teamProject.user.dto.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 리뷰 댓글 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewComment {
    private long commentNo; //댓글번호
    private long orderItemNo; //주문상품 번호 - fk 없음
    private Review review; //리뷰
    private Member member; //회원
    private String text; //내용
    private Date regDate; //작성일자
    private Date modDate; //수정일자
    private long orderNo; //주문번호
}
