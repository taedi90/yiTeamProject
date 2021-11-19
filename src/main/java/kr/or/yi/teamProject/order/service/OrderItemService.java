package kr.or.yi.teamProject.order.service;

import java.util.List;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;

public interface OrderItemService {
	
	
	int insertOrderItem(OrderItem orderItem);
	int updateOrderItem(OrderItem orderItem);
	int deleteOrderItem(OrderItem orderItem);
	List<OrderItem> orderItemList(OrderItem orderItem);
	
	
}
