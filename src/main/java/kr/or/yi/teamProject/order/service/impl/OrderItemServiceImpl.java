package kr.or.yi.teamProject.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.order.mapper.OrderItemMapper;
import kr.or.yi.teamProject.order.mapper.OrderMapper;
import kr.or.yi.teamProject.order.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;


	@Override
	public int insertOrderItem(OrderItem orderItem) {
		return orderItemMapper.insertOrderItem(orderItem);
	}


	@Override
	public int updateOrderItem(OrderItem orderItem) {
		return orderItemMapper.updateOrderItem(orderItem);
	}


	@Override
	public int deleteOrderItem(OrderItem orderItem) {
		return orderItemMapper.deleteOrderItem(orderItem);
	}


	@Override
	public List<OrderItem> orderItemList(OrderItem orderItem) {
		return orderItemMapper.orderItemList(orderItem);
	}




}
