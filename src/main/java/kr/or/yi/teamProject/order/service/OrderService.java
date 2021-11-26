package kr.or.yi.teamProject.order.service;

import java.util.List;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;

public interface OrderService {

	int insertOrderMember(Order order);
	List<Order> selectOrderList();
//	public void createOrderMember(Order order, OrderItem orderItem);
	public int createOrderMember(Order order, List<OrderItem> orderItems);
	
}
