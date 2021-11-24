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
	public void createOrderMember(Order order, OrderItem orderItem) {
		int res =  orderMapper.insertOrderMember(order);
		
		if(res == 1 ) {
			
			orderItemMapper.insertOrderItem(orderItem);
		}	

	}

	@Override
	public List<Order> selectOrderList() {

		return orderMapper.selectOrderList();
	}


	


}
