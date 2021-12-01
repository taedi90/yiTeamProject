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
import org.springframework.security.core.Authentication;
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
import kr.or.yi.teamProject.order.mapper.OrderDetailMapper;
import kr.or.yi.teamProject.order.mapper.OrderMapper;
import kr.or.yi.teamProject.order.service.OrderDetailService;
import kr.or.yi.teamProject.order.service.OrderItemService;
import kr.or.yi.teamProject.order.service.OrderService;
import kr.or.yi.teamProject.product.controller.ItemController;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.product.mapper.OptionMapper;
import kr.or.yi.teamProject.product.service.ItemService;
import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Member;
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
	OrderDetailMapper orderDetailMapper;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	OptionMapper optionMapper;

	// 주문대기열 추가
	@PostMapping("/order")
	@ResponseBody
	public String insertOrder(@RequestBody List<Map<String, Object>> list, Model model, Authentication authentication) {
		
		log.info("=====================insertOrder========================");
		
		
		// 로그인 정보 가져오기
		CustomUser user = (CustomUser) authentication.getPrincipal();
		Member member = user.getMember();

		
		// 주문번호 난수 생성
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
		long orderno = (parseNow * 100000) + rndnum;
		
		System.out.println("==============orderno==============" + orderno);

		
		// order 객체생성
		Order order = new Order();
		order.setOrderNo(orderno);
		order.setMember(member);
		order.setStatus("주문대기");
		
		
		// 전체가격 선언(배송비 여부)
		int amount = 0;


		// 해쉬맵 리스트를 풀어서 orderItems에 담기
		List<OrderItem> orderItems = new ArrayList<>();

		for (Map<String, Object> m : list) {

			log.info("Long.parseLong(m.get(\"optionNo\").toString())" + Long.parseLong(m.get("optionNo").toString()));

			OrderItem orderItem = new OrderItem();
			Option option = optionMapper.selectOptionDetail(Long.parseLong(m.get("optionNo").toString()));
			
			int quantity = Integer.parseInt(m.get("quantity").toString());	
			

			//재고확인	 재고 부족시 널값 리턴
			Option stockCk = optionMapper.selectOption(option);
			int stock = stockCk.getStock();
			log.info(stockCk.toString());	
			System.out.println("=========stock========"+stock);
			System.out.println("=========quantity========"+quantity);
			if(quantity>stock) {
				return "";
			}	
			
		
			//주문금액 함계 구하기
			int optionPrice = option.getOptionPrice();
			Item item = option.getItem();
			int itemPrice = item.getPrice();
			
			amount += (itemPrice + optionPrice);

			log.info("==============option===============" + option);

			orderItem.setOption(option);
			orderItem.setOrder(order);

			
			for (int i = 0; i < Integer.parseInt(m.get("quantity").toString()); i++) {
				orderItems.add(orderItem);
			}
		
			
		}

		
		//총결제 금액확인
		System.out.println("============amount============"+amount);

		log.info("============객체확인===========" + order.toString() + orderItems.toString());

		
		// 주문대기열 추가
		int res = orderService.createOrderMember(order, orderItems);
		if (res == 1) {
			return String.valueOf(orderno);
		}
		return "";

	}
	

	// 주문 페이지
	@GetMapping("/order")
	public String order(@RequestParam("orderNo") Long orderNo, Model model) {

		log.info("========================order=============================");

		Order orderDetail = orderDetailService.selectDetailOrder(Order.builder().orderNo(orderNo).build());

		model.addAttribute("orderDetail", orderDetail);
		log.info(orderDetail.toString());

		return "/order/order";
	}

	
	// 주문 update
	@PostMapping("/orderUpdate")
	@ResponseBody
	public String orderUpdate(@RequestBody Map<String, Object> updateData, Model model) {

		log.info("======================orderUpdate===================");
		log.info(updateData.toString());

		//객체생성
	     Order order = new Order();
	     
	     order.setAddress1(updateData.get("address1").toString());
	     order.setAddress2(updateData.get("address2").toString());
	     order.setZipcode(updateData.get("zipcode").toString());
	     order.setName(updateData.get("name").toString());
	     order.setPhone(updateData.get("phone").toString());
	     order.setOrderNo(Long.parseLong(updateData.get("orderNo").toString()));
	     order.setStatus("걸제대기");
	     
	     Long orderNo = Long.parseLong(updateData.get("orderNo").toString());
	     
	     int res = orderMapper.updateOrder(order);
	     
	     log.info("==========result============"+res);   
	     
	     if(res == 1 ) {
	        return String.valueOf(orderNo);
	     }
	     
	     return"";
	}


}
