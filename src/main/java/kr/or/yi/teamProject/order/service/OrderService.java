package kr.or.yi.teamProject.order.service;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;

public interface OrderService {

	int insertOrderMember(Order order);
	public void createOrderMember(Order order, OrderItem orderItem);
	
}
