package kr.or.yi.teamProject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 상품 정보 제공고시 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoAnnounce {
    private int piaNo; //고시 번호
    private String title; //템플릿 명
    private String text; //내용
    private Date regDate; //작성일
}
