package kr.or.yi.teamProject.payment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import kr.or.yi.teamProject.payment.dto.Payment;
import kr.or.yi.teamProject.payment.mapper.PaymentMapper;
import kr.or.yi.teamProject.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentMapper mapper;

	@Override
	public List<Payment> selectPaymentUid(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Payment> selectPaymentList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertPaymentOrder(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPayment(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertRefund(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePaymentUid(String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePaymentOrderNo(Payment payment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
