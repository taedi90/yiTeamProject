package kr.or.yi.teamProject.payment.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.yi.teamProject.payment.dto.Payment;

@Mapper
public interface PaymentMapper {
	
	List<Payment> selectPaymentUid(Payment payment);
	ArrayList<Payment> selectPaymentList();
	
	int updatePaymentOrder(Payment payment);
	
	int insertPayment(Payment payment);
	int insertRefund(Payment payment);
	
	int updatePayment(Payment payment);
	
	int deletePaymentUid(String uid);
	int deletePaymentOrderNo(Payment payment);
	
	

}
