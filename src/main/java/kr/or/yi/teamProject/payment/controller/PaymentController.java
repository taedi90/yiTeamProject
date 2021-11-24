package kr.or.yi.teamProject.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.Order.OrderBuilder;
import kr.or.yi.teamProject.payment.dto.Payment;
import kr.or.yi.teamProject.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
//	@Autowired
//	PaymentService paymentService;
//	
//		
//	@GetMapping("/payment")
//	public String paymentInfo(Payment payment, Order order) {	
//					
//		order.setOrderNo(order.getOrderNo());
//			
//		payment.setOrder(order);
//						
//		paymentService.insertPaymentOrder(payment);
//			
//		return null;
//			
//	}
	
	
	
//	@PostMapping("/payment/success")
//	public String postSuccess(@RequestBody Payment payment) {
//		log.info(payment.toString());
//		
//		return null;
//	}

}
