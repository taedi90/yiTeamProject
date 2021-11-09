package kr.or.yi.teamProject.order.dto;

import java.util.Date;

import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.dto.NonMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private long orderNo;		//주문번호
	private Member member;		//회원아이디
	private NonMember nonMember;//비회원 아이디
	private String zipcode;		//우편번호
	private String address1;	//주소앞	
	private String address2;	//주소 상세
	private String name;		//받는분
	private String phone;		//휴대폰
	private Date regDate;		//생성일자
	private int point;			//포인트 사용액
	private String status;		//주문상태
	
}