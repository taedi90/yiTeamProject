package kr.or.yi.teamProject.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderDetail;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.order.service.OrderDetailService;
import kr.or.yi.teamProject.order.service.OrderItemService;
import kr.or.yi.teamProject.order.service.OrderService;
import kr.or.yi.teamProject.product.controller.ItemController;
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
  public String order(Model model, OrderItem orderItem) {
	  
	  OrderItem order = OrderItem.builder().order(Order.builder().orderNo(5L).build()).build();	
	  List<OrderItem> orderItemList = orderItemService.orderItemList(order);	
	  
	  System.out.println("=============orderItemList================"+orderItemList);
	  
	  
	  model.addAttribute("orderItemList",orderItemList);  

  	return "order/order";
  }
  
  //주문조회 new
  @PostMapping("order")
  public String selectOrder(Model model, OrderItem orderItem) {
	  
	  OrderItem order = OrderItem.builder().order(Order.builder().orderNo(5L).build()).build();	
	  List<OrderItem> orderItemList = orderItemService.orderItemList(order);	
	  
	  System.out.println("=============orderItemList================"+orderItemList);
	  
	  
	  model.addAttribute("orderItemList",orderItemList);  

  	return "order/order";
  }
  
  

  
  



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
	
	
}
