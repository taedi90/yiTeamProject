package kr.or.yi.teamProject.order.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
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
import kr.or.yi.teamProject.order.service.OrderService;
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
public class OrderMapperTest {

	@Autowired
	private OrderMapper mapper;
	private Order order;
	
	@Autowired
	private OrderService service;

	
    //매 테스트마다 실행 - before, beforeEach
    @Before
    public void setUp(){
        //테스트용 category 객체 생성
		//insert객체생성
    	order = new Order();
    	order.setOrderNo(2L);		
    	order.setMember(Member.builder().username("test").build());
    }
    

	@After
	public void tearDown() {
        log.info("종료 후 DB 내역 --->");
	}
	
	
	

	@Test
	public void test0_txTest() {
		log.info("=====================test0_txTest 시작==============");
		
		Order order = new Order();

		order.setOrderNo(5L);		
		order.setMember(Member.builder().username("test").build());

		
		OrderItem orderitem = new OrderItem();
		
		Category category = Category.builder().categoryNo(1).build();
		Item item = Item.builder().itemNo(61)
					.category(category).build();
		
		Option option = Option.builder().optionNo(95).name("아우터")
						.item(item)
						.build();

		orderitem.setOrder(order);	
		orderitem.setOption(option);
		orderitem.setPriceItem(3000);
		orderitem.setPriceOption(3000);
		service.createOrderMember(order, orderitem);
	}
	
	
	
	
//	@Test
	public void test01_orderInsert() {
		log.info("=====================orderInsertTest 시작==============");
		
//		insert
		int res = mapper.insertOrderMember(order);

//		결과확인
		assertEquals(1, res);
		test03_orderSelectList();
		
		
	}
	
//	@Test
	public void test02_orderSelect() {
		log.info("=================orderSelectTest 확인===================");
		
		//select
		Order resultDto = mapper.selectOrder(Order.builder().orderNo(1L).build());
		
		//결과확인
		assertNotNull(resultDto);

	}
	
//	@Test
	public void test03_orderSelectList() {
		log.info("=================orderSelectListTest 확인===================");
		
		//selectList
		List<Order> resList = mapper.selectOrderList();

		//결과확인
		assertTrue(resList.size() > 0);
		
	}
	
	
//	@Test
	public void test04_orderUpdate() {
		log.info("=================orderUpdateTest 확인===================");
		
		//update할 객체생성
		order = new Order();
		order.setOrderNo(5L); 
		order.setZipcode("12345");
		order.setAddress1("updateAddress1");
		order.setAddress2("updateAddress2");
		order.setName("updateName");
		order.setPhone("01099998888");
	
		//update
		int res = mapper.updateOrder(order);
		
		//결과확인
		assertEquals(1, res);

	}

	
//	@Test
	public void test05_orderDelete() {
		log.info("=================orderDeleteTest 확인===================");
		
		//delete
		int res = mapper.deleteOrder(Order.builder().orderNo(5L).build());
		
		//결과확인
		assertEquals(1, res);

	}	
	
	
	
}
