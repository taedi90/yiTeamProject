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
	


	@Test
    public void _01_selectOrderByNo() {
        log.info("=== order_no로 조회 ===");
   
        Order result = mapper.selectDetailOrder(Order.builder().orderNo(1L).build());
        log.info("조회 결과 -> " + result.toString());
        assertNotNull(result);
    }
	
	


	
	
	
}
