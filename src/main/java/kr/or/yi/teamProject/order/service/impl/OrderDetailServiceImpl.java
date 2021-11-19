package kr.or.yi.teamProject.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.order.mapper.OrderDetailMapper;
import kr.or.yi.teamProject.order.mapper.OrderItemMapper;
import kr.or.yi.teamProject.order.mapper.OrderMapper;
import kr.or.yi.teamProject.order.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	
	@Autowired
	private OrderDetailMapper orderDetailMapper;	

	
	
	@Override
	public Order selectDetailOrder(Order order) {
		return orderDetailMapper.selectDetailOrder(order);
	}


}
