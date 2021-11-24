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

		OrderItem orderitem = new OrderItem();
		
		Category category = Category.builder().categoryNo(1).build();
		Item item = Item.builder().itemNo(293)
					.category(category).build();
		
		Option option = Option.builder().optionNo(352).name("엠사이쥬")
						.item(item)
						.build();

		
	

		orderitem.setOrder(Order.builder().orderNo(5L).build());
		orderitem.setOption(option);
		orderitem.setPriceItem(3000);
		orderitem.setPriceOption(3000);
	
		
//		//insert
		int res = mapper.insertOrderItem(orderitem);
	
//		//결과확인
		assertEquals(1, res);
				
	}

//	@Test
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
