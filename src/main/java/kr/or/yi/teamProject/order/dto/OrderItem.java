package kr.or.yi.teamProject.order.dto;

import java.util.Date;

import kr.or.yi.teamProject.manage.dto.Carrier;
import kr.or.yi.teamProject.manage.dto.Coupon;
import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.user.dto.CouponHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	
	private long orderItemNo;		//주문상품 번호 
	private Order order;			//주문번호
	private Option option;			//옵션번호 + 카테고리번호 + 상품번호
	private String optionName;		//옵션명 추후변동가능
	private CouponHistory couponHistory; //쿠폰발급번호 + 쿠폰번호
	private int priceItem;			//상품 금액
	private int priceOption;		//옵션 금액
	private int priceCoupon;		//할인 금액
	private Date prepareDate;		//배송 준비일
	private Carrier carrier;		//택배사 번호
	private int trackingNumber;		//배송_운송장번호
	private Date deliveryStartDate;	//배송_운송장등록일
	private Date deliveryEndDate;	//배송_완료일
	private Date confirmDate;		//구매_확정일
	private Date cancelDate;		//취소-요청일
	private Date returnDate;		//반품_요청일
	private int returnCarrierNo;	//반품_택배사번호
	private Date returnNumber;		//반품_운송장
	private Date returnConfirmDate;	//반품_처리일
	private ReturnOption returnOption; //반품_코드
	private Date refundDate;		//환불_일자

}

