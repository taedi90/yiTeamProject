package kr.or.yi.teamProject.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import kr.or.yi.teamProject.payment.dto.Payment;
import kr.or.yi.teamProject.payment.mapper.PaymentMapper;

public class PaymentServiceImpl {
	
	@Autowired
	private PaymentMapper mapper;
//	
//	//주문금액과 결제금액이 같은지 확인, 주문번호 참조?
//	@Override
//	public void paymentValidation(Payment payment, Order order) {
//		if(payment.getOrder().)
//		
//		return null;
//	}
//	
//	//imp번호로 주문이 유효한지 확인
//	//금액, 구매자정보, 주문번호 등등

}
