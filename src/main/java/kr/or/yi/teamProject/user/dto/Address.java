package kr.or.yi.teamProject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 주소 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Member member; //회원
    private String title; //배송지 별칭
    private String zipcode; //우편번호
    private String address1; //주소 앞
    private String address2; //주소 상세
    private String name; //받는분
    private String phone; //휴대폰
    private Date regDate; //등록일자
    private Date modDate; //수정일자
}
