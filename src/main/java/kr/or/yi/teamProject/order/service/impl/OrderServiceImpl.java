package kr.or.yi.teamProject.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.order.mapper.OrderItemMapper;
import kr.or.yi.teamProject.order.mapper.OrderMapper;
import kr.or.yi.teamProject.order.service.OrderService;
import kr.or.yi.teamProject.product.dto.Option;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;

	
	@Override
	public int insertOrderMember(Order order) {
		return orderMapper.insertOrderMember(order);
	}
	
	
//	회원 주문 대기열 트랜잭션 이용해서 order테이블 다음에 orderItem테이블 insert
	@Transactional
	@Override
	public int createOrderMember(Order order, List<OrderItem> orderItems) {
		
		int res1 =  orderMapper.insertOrderMember(order);
		
		int res2 = 0;
		
		if(res1 == 1 ) {
			for(OrderItem i : orderItems) {
								
				res2 = orderItemMapper.insertOrderItem(i);
			}
			
		}	
			return res2;
	}
	

	
//	회원 주문 대기열 old
//	@Transactional
//	@Override
//	public void createOrderMember(Order order, OrderItem orderItem) {
//		int res =  orderMapper.insertOrderMember(order);
//		
//		if(res == 1 ) {
//				orderItemMapper.insertOrderItem(orderItem);
//			}	
//	}


	
	
	
	
	
	@Override
	public List<Order> selectOrderList() {

		return orderMapper.selectOrderList();
	}


	


}
