package kr.or.yi.teamProject.payment.dto;

import kr.or.yi.teamProject.order.dto.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	private String paymentNo;
	private String sort;
	private String method;
	private Order order; //order.dto
	private String uid;
	private int amount;
	private Data regData;
	
}