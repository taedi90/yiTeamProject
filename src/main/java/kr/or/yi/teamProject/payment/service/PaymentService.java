package kr.or.yi.teamProject.payment.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.yi.teamProject.payment.dto.Payment;

public interface PaymentService {
	
	List<Payment> selectPaymentUid(Payment payment);
	ArrayList<Payment> selectPaymentList();
	
	int insertPaymentOrder(Payment payment);
	int insertPayment(Payment payment);
	int insertRefund(Payment payment);
	
	int updatePayment(Payment payment);
	
	int deletePaymentUid(String uid);
	int deletePaymentOrderNo(Payment payment);
}
