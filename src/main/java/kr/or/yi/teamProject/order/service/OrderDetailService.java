package kr.or.yi.teamProject.order.service;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;

public interface OrderDetailService {
	Order selectDetailOrder(Order order);
	
	

}
