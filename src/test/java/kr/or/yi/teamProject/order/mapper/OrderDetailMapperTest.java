package kr.or.yi.teamProject.order.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderDetail;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.product.dto.Category;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.user.dto.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class OrderDetailMapperTest {

	@Autowired
	private OrderDetailMapper mapper;
	private Order dto;
	

	@After
	public void tearDown() {
		System.out.println();
	}
	

	@Ignore
	@Test
    public void _01_selectOrderByNo() {
        log.info("=== order_no로 조회 ===");
        Long num = Long.parseLong("2021112515001281366");
   
        Order result = mapper.selectDetailOrder(Order.builder().orderNo(num).build());
        log.info("조회 결과 -> " + result.toString());
        assertNotNull(result);
    }
	
	@Ignore	
	@Test
	public void _01_selectOrderByMember() {
		log.info("=== order_no로 조회 ===");
		Member member = Member.builder().username("google_tmddk65220@gmail.com").build();
		 
		log.info(member.toString());
		
		List<OrderDetail> orderDetailSelectList = mapper.orderDetailSelectList(member);
		
		log.info(orderDetailSelectList.toString());
	}

	@Test
	public void _01_selectOrderList() {
		log.info("=== List로 조회 ===");
	
		List<OrderDetail> orderDetailSelectList = mapper.ordetDetailList();
		
		for(Order i : orderDetailSelectList) {
			log.info(i.toString());
		}
		
		log.info(orderDetailSelectList.toString());
	}
	
	


	
	
	
}
