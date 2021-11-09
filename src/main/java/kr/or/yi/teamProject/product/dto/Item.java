package kr.or.yi.teamProject.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 상품정보 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int itemNo; //상품번호
    private Category category; //카테고리
    private String name; //상품명
    private int price; //가격
    private int discount; //할인 가격
    private Date startDiscount; //할인 시작일
    private Date endDiscount; //할인 종료일
    private String title; //제목
    private String text; //내용
    private String image; //상품 이미지
    private ProductInfoAnnounce productInfoAnnounce; //상품 정보 제공고시
    private boolean couponAllow; //쿠폰 할인 가능여부
    private boolean hide; //상품 숨기기
}
