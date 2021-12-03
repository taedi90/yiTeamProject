package kr.or.yi.teamProject.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderDetail;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.user.dto.Member;

@Mapper
public interface OrderDetailMapper {

	Order selectDetailOrder(Order order);	
	
	List<OrderDetail> ordetDetailList();
	List<OrderDetail> orderDetailSelectList(Member member);
	
	List<OrderDetail> selectOrderList(Pager pager);
	Pager getInfoForPaging(Pager pager);

}
