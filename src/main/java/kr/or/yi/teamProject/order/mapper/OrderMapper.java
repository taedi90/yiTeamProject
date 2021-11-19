package kr.or.yi.teamProject.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.yi.teamProject.order.dto.Order;

@Mapper
public interface OrderMapper {
	
	List<Order> selectOrderList();
	Order selectOrder(Order order);
	
	int insertOrderMember(Order order);
//	int insertOrderNonMember(Order order);
	int updateOrder(Order order);
	int deleteOrder(Order order);
	
	

}
