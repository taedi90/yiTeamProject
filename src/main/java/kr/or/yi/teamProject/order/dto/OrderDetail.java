package kr.or.yi.teamProject.order.dto;

import java.util.List;

import kr.or.yi.teamProject.payment.dto.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends Order{
	private int cnt; // orderitem 수량확인
	private List<OrderItem> itemList;
	private List<Payment> paymentList;

}
