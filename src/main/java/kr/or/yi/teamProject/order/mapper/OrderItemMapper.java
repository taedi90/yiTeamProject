package kr.or.yi.teamProject.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderItem;

@Mapper
public interface OrderItemMapper {
	
	int insertOrderItem(OrderItem orderItem);
	int updateOrderItem(OrderItem orderItem);
	int deleteOrderItem(OrderItem orderItem);
	List<OrderItem> orderItemList(OrderItem orderItem);

}
