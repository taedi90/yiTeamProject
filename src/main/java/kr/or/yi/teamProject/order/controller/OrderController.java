package kr.or.yi.teamProject.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderDetail;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.order.service.OrderDetailService;
import kr.or.yi.teamProject.order.service.OrderItemService;
import kr.or.yi.teamProject.order.service.OrderService;
import kr.or.yi.teamProject.product.controller.ItemController;
import kr.or.yi.teamProject.product.dto.Option;
import lombok.extern.slf4j.Slf4j;


//OrderController관련컨트롤러
@Slf4j
@Controller
public class OrderController {
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrderService orderService;
	
	
	

    // 주문 조회	old
  @GetMapping("/order")
  public String order(Model model, Order order) {

	  
	  
	  order = Order.builder().orderNo(5L).build();
	  
	  Order order1 = orderDetailService.selectDetailOrder(order);
	  
	  System.out.println("=============orderItemList================"+order1);
	  
	  
	  model.addAttribute("orderItemList",order1); 

  	return "order/order";
  }
  
  // 주문 조회	old
//  @GetMapping("/order")
//  public String order(Model model, OrderItem orderItem) {
//	  
//	  
//	  OrderItem order = OrderItem.builder().order(Order.builder().orderNo(5L).build()).build();	
//	  List<OrderItem> orderItemList = orderItemService.orderItemList(order);	
//	  
//	  System.out.println("=============orderItemList================"+orderItemList);
//	  
//	  
//	  model.addAttribute("orderItemList",orderItemList);  
//	  
//	  return "order/order";
//  }
  
  //주문조회 new
  @PostMapping("selectOrder")
  public String selectOrder(Model model, Order order) {

	  
	  order = Order.builder().orderNo(5L).build();
	  
	  Order order1 = orderDetailService.selectDetailOrder(order);
	  
	  System.out.println("=============orderItemList================"+order1);
	  
	  
	  model.addAttribute("orderItemList",order1);  

  	return "order/order";
  }

  
  
  //주문대기열 추가
  @PostMapping("/order")
  public String insertOrder(@RequestBody List<Map<String, Object>>list, Model model) {
	  	
	 
	  	//주문번호 난수 생성
	  	Order order = Order.builder().orderNo(5L).build();
  	
	  	// 해쉬맵 리스트를 풀어서 orderItems에 담기
	  	List<OrderItem> orderItems =new ArrayList<>();
	  	
		for(Map<String, Object> m : list) {

			OrderItem orderItem = new OrderItem();
			
			orderItem.setOption(Option.builder().optionNo(Long.parseLong(m.get("optionNo").toString())).build());
			
				for(int i = 0; i<Integer.parseInt(m.get("quantity").toString());i++) {
					
					orderItems.add(orderItem);
				
				}

		}

		//주문대기열 추가
		orderService.createOrderMember(order, orderItems);
		
		
	  return "order/order";
  }
  
  

  
  



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
	
	
}
