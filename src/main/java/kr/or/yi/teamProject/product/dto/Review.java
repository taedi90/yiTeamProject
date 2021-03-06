package kr.or.yi.teamProject.product.dto;

import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.order.dto.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 리뷰 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private long reviewNo; //리뷰번호
    private OrderItem orderItem; //주문상품
    private Member member; //회원
    private int star; //별점
    private String text; //내용
    private String title; //제목
    private Date regDate; //작성일
    private Date modDate; //수정일
    private String answerText; //답변 내용
    private String answerId; //답변 담당자
    private Date answerRegDate; //답변 작성일
    private Date answerModDate; //답변 수정일
}
