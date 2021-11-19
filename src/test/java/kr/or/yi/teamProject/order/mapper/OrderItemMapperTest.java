package kr.or.yi.teamProject.order.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
import kr.or.yi.teamProject.order.service.OrderItemService;
import kr.or.yi.teamProject.order.service.impl.OrderItemServiceImpl;
import kr.or.yi.teamProject.order.service.impl.OrderServiceImpl;
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
public class OrderItemMapperTest {

	@Autowired
	private OrderItemMapper mapper;
	


//	@Test
	public void test01_orderItemInsert() {
		log.info("================orderItemInsert====================");

//		객체생성
		OrderItem dto = new OrderItem();
		
		Category category = Category.builder().categoryNo(1).build();
		Item item = Item.builder().itemNo(61)
					.category(category).build();
		
		Option option = Option.builder().optionNo(95).name("아우터")
						.item(item)
						.build();
		

		dto.setOrder(Order.builder().orderNo(4L).build());
		dto.setOption(option);
		dto.setPriceItem(3000);
		dto.setPriceOption(3000);
	
		
//		//insert
		int res = mapper.insertOrderItem(dto);
	
//		//결과확인
		assertEquals(1, res);
				
	}

	@Test
	public void test03_orderItemSelectList() {
		log.info("=================orderSelectListTest 확인===================");
		
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(Order.builder().orderNo(1L).build());
		log.info(orderItem.toString());
		
		
		//selectList
		List<OrderItem> resList = mapper.orderItemList(orderItem);


		//결과확인
		assertTrue(resList.size() > 0);
		
	}	
	
	
}
