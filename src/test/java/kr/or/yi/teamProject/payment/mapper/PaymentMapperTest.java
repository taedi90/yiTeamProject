package kr.or.yi.teamProject.payment.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.payment.dto.Payment;
import lombok.extern.slf4j.Slf4j;

@WebAppConfiguration
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentMapperTest {

	@Autowired
	private PaymentMapper mapper;
	
	private Payment payment;
	
	private Order order;
	
	List<Payment> paymentList;
	
	private ArrayList<Payment> paymentArrayList;
	
	
	//매 테스트 종료 시 마다 실행
    @After
    public void tearDown() throws Exception {
        log.info("종료 후 DB 내역 --->");
        paymentArrayList = selectPaymentList();
    }
	
	
	// 결제테이블 입력 테스트(결제)
    //@Test
	public void _01_testInsertPayment() {
		log.info("=== 결제정보 생성 ===");
		payment = new Payment();
		payment.setSort("결제");
		payment.setMethod("card");
		payment.setOrder(null); // orderMapper 만들어지면 거기서 테스트 오더 만든 후 그거 사용
		payment.setUid("test1");
		payment.setAmount(500);

		int result = mapper.insertPayment(payment);

		log.info("=========================");
		log.info(payment.toString());
		log.info("=========================");
		
		assertEquals(1, result);

	}

	
	
	// 결제테이블에서 uid를 이용해 특정 정보 불러오기 (오더 테스트 만들어지면 주문번호 이용해서 불러오기 가능)
	//@Test
	public void _02_testselectPaymentUid() {
		log.info("=== pg사 결제번호에 따른 검색 ===");
		
		paymentList = mapper.selectPaymentUid(Payment.builder().uid("test1").build());
		
		System.out.println(paymentList);
		
		assertTrue(paymentList.size() > 0);

	}
	
	
	
	// 결제테이블 입력 테스트(환불)
	//@Test
	public void _03_testInsertRefund() {
		log.info("=== 환불정보 생성 ===");
		payment = new Payment();
		payment.setSort("환불");
		payment.setMethod("card");
		payment.setOrder(null); // orderMapper 만들어지면 거기서 테스트 오더 만든 후 그거 사용
		payment.setUid("test1");
		payment.setAmount(200);

		mapper.insertRefund(payment);

		log.info("=========================");
		log.info(payment.toString());
		log.info("=========================");

	}
	
	
	// pg사 결제번호에 따른 결제정보 수정
	//@Test
	public void _04_testupdatePayment() {
		log.info("=== pg사 결제번호를 골라 데이터 수정 ===");
		payment = new Payment();
		
		payment.setUid("test1");
	
		payment.setAmount(1000);

	}
	
	
	
	// pg사 결제번호에 따른 결제정보 삭제
	//@Test
	public void _05_testdeletePaymentUid() {
		log.info("=== pg사 결제번호를 골라 데이터 삭제 ===");
		mapper.deletePaymentUid("test1");
	}
	
	
	
	public ArrayList<Payment> selectPaymentList() {
        ArrayList<Payment> paymentArrayList = mapper.selectPaymentList();

        return paymentArrayList;
    }
	
}
