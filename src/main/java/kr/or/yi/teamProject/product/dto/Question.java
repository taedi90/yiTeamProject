package kr.or.yi.teamProject.product.dto;

import kr.or.yi.teamProject.user.dto.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 질문 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private long questionNo; //질문번호
    private Member member; //회원
    private Item item; //상품
    private String title; //제목
    private boolean secret; //비밀글 여부
    private String text; //내용
    private Date regDate; //작성일
    private String answerTitle; //답변 제목
    private String answerText; //답변 내용
    private String answerId; //답변 작성자
    private Date answerRegDate; //답변 작성일
}
