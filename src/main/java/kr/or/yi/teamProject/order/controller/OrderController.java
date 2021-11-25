package kr.or.yi.teamProject.order.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
  public String order(@RequestParam("orderNo")Long orderNo, Model model) {
	  	  	  
	  Order order = Order.builder().orderNo(orderNo).build();
	  
	  Order order1 = orderDetailService.selectDetailOrder(order);
	  
	  System.out.println("=============orderItemList================"+order1);
	  
	  
	  model.addAttribute("orderItemList",order1); 

  	return "order/order";
  }

  
  
  //주문대기열 추가
  @PostMapping("/order")
  @ResponseBody
  public String insertOrder(@RequestBody List<Map<String, Object>> list, Model model) {
	  
	
	  log.info("=====================insertOrder========================");
	  log.info(list.toString());

		//	  옵션 유효성 검사
		//	  재고 확인
		  
	  //주문번호 난수 생성
	  SecureRandom rnd = null;
      try {
          rnd = SecureRandom.getInstance("SHA1PRNG");
      } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
      }
	  int rndnum = rnd.nextInt(100000);
	  
	  String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	  long parseNow = Long.parseLong(now);
	  System.out.println(now);
	  long orderno =  (parseNow * 100000) + rndnum;
	  
	  System.out.println("==============orderno=============="+orderno);
	  

	  Order order = Order.builder().orderNo(orderno).build();
	   
	   
	   // 해쉬맵 리스트를 풀어서 orderItems에 담기
	   List<OrderItem> orderItems =new ArrayList<>();
	   
	   for(Map<String, Object> m : list) {
		   log.info("===============m========="+m);
		   log.info("Long.parseLong(m.get(\"optionNo\").toString())"+Long.parseLong(m.get("optionNo").toString()));
	      
	      OrderItem orderItem = new OrderItem();
	      
	      orderItem.setOption(Option.builder().optionNo(Long.parseLong(m.get("optionNo").toString())).build());
	      orderItem.setOrder(order);
	      
	      
	      for(int i = 0; i<Integer.parseInt(m.get("quantity").toString());i++) {
	         
	         
	         orderItems.add(orderItem);
	         log.info("===============orderItems.toString()==============="+orderItems.toString());
	      }
	      
	   }
	   
	   
	   log.info("==========================================================");
	   log.info(order.toString());
	   log.info(orderItems.toString());
	   
	   //주문대기열 추가
	   int res = orderService.createOrderMember(order, orderItems);
	   if(res == 1 ) {
		   return String.valueOf(orderno);
	   }
	   	   
	   return "";

  }  
  
  


	
	
}
