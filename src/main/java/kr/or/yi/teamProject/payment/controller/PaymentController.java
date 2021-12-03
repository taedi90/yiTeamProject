package kr.or.yi.teamProject.payment.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.maven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
import com.nimbusds.oauth2.sdk.Request;

import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.Order.OrderBuilder;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.order.mapper.OrderItemMapper;
import kr.or.yi.teamProject.order.mapper.OrderMapper;
import kr.or.yi.teamProject.payment.dto.Payment;
import kr.or.yi.teamProject.payment.mapper.PaymentMapper;
import kr.or.yi.teamProject.payment.service.PaymentService;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.product.mapper.ItemMapper;
import kr.or.yi.teamProject.product.mapper.OptionMapper;
import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Cart;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.mapper.CartMapper;
import kr.or.yi.teamProject.user.service.CartService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	PaymentMapper paymentMapper;

	@Autowired
	OrderItemMapper orderItemMapper;
	
	@Autowired
	OptionMapper optionMapper;
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	ItemMapper itemMapper;
	
	@Autowired
	CartMapper cartMapper;
	
	@Autowired
	CartService cartService;

	// order에서 주문번호 받아오기
	@PostMapping("/payment")
	@ResponseBody
	public Map<String, String> insertPaymentOrderNo(@RequestBody Map<String, String> map, Model model,
			Authentication authentication) {

		// 주문번호를 토대로 회원정보 조회, 본인 주문 맞는지 확인

		// 로그인 정보 확인
		CustomUser user = (CustomUser) authentication.getPrincipal();
		Member member = user.getMember();

		
		
		

		// 주문번호로 orderItem 리스트 불러오기
		log.info("=============orderNo 뽑기====================");
		log.info(map.get("orderNo").toString());

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(Order.builder().orderNo(Long.parseLong(map.get("orderNo").toString())).build());

		log.info("=============orderItem====================");
		log.info(orderItem.toString());

		List<OrderItem> orderItemList = orderItemMapper.orderItemList(orderItem);	//주문번호에 해당하는 orderItem 리스트를 불러옴

		log.info("=============itemList====================");
		log.info(orderItemList.toString());
		
		
		// list 크기 구하기
		log.info("=============list 크기 구하기====================");
		System.out.println(orderItemList.size());
		
		
		
		
		
		// orderItem으로 재고여부 확인 (optionNo)
		List<Long> optionList = new ArrayList<>();
		// list를 for문으로 돌려 옵션넘버 구하기
		for(int i = 0; i < orderItemList.size(); i++) {
			
			Long orderItemOptionNo = orderItemList.get(i).getOption().getOptionNo();
			
			log.info("=============optionNo 구하기====================");
			log.info(orderItemOptionNo.toString());
			
			optionList.add(orderItemOptionNo);
			log.info("=============option====================");
			log.info(optionList.toString());
		}
		
		log.info("=============for문 밖 option====================");
		log.info(optionList.toString());
		
		// 옵션번호에 따른 주문 수량 구하기
		log.info("=============리스트 중복검사(옵션번호에따른 수량구하기)====================");
		Map<String, Long> opt = new HashMap<String, Long>();
		List<Map<String, Long>> list = new ArrayList<Map<String,Long>>();
		
		Set<Long> set = new HashSet<Long>(optionList);
		for(Long optionNo : set) {
			
			int quantity = Collections.frequency(optionList, optionNo);
			System.out.println(optionNo + ":" + quantity);
			
//			opt.add("quantity", quantity);
			opt.put("optionNo", optionNo);
			opt.put("quantity", (long)quantity);
			
			list.add(opt);
		}
		
		log.info("=============Hashmap 검사====================");
		log.info(opt.toString());
		
		log.info("=============Hashmap list 검사====================");
		log.info(list.toString());
		
		
		
		// 전체가격 선언
	    int amount = 0;
	    String productName = null;
		
	    // 주문상품 상품명 리스트에 담기
	    List<String> itemNameList = new ArrayList<>();
	    
		//해시맵에 담긴 옵션번호와 주문수량으로 재고확인
		List<OrderItem> orderItems = new ArrayList<>();
		

	    for (Map<String, Long> m : list) {


	         Option option = optionMapper.selectOptionDetail(Long.parseLong(m.get("optionNo").toString()));
	         
	         int quantity = Integer.parseInt(m.get("quantity").toString());
	         
	         //재고확인    재고 부족시 널값 리턴
	         Option stockCk = optionMapper.selectOption(option);
	         int stock = stockCk.getStock();
	         log.info(stockCk.toString());   
	         System.out.println("=========stock========"+stock);
	         System.out.println("=========quantity========"+quantity);
	         
	         if(quantity > stock) {
	        	 System.out.println("재고부족");
	        	 log.info("재고부족");
	        	 return null;
	         }
	         
	         
	         
	         // 주문금액 합계 구하기
	         int optionPrice = option.getOptionPrice();
	         Item item = option.getItem();
	         int itemPrice = item.getPrice();
	         
	         String itemName = item.getName();
	         String itemTitle = item.getTitle();
	         
	         log.info("===========상품명=========");
	         itemNameList.add(itemTitle);
	         System.out.println(item);
	         System.out.println(itemName);
	         System.out.println(itemTitle);
	         
	         amount += (itemPrice + optionPrice);

	         log.info("==============option===============" + option);

	         orderItem.setOption(option);
	         orderItem.setOrder(Order.builder().orderNo(Long.parseLong(map.get("orderNo").toString())).build());
	         
	         
	         
	         System.out.println("*********** itemNameList **********" + itemNameList);
	         
	         if(itemNameList.size()>1) {
	        	 productName = itemNameList.get(0) + " 등";
	         } else {
	        	 productName = itemNameList.get(0);
	         }
	         
	         for (int i = 0; i < Integer.parseInt(m.get("quantity").toString()); i++) {
	            orderItems.add(orderItem);
	         }
	        
	         
	      }
	    
	    // 상품명 확인
	    System.out.println("********* productName **********" + productName);
	    
	    
	    //총 상품 금액확인	
	    System.out.println("============amount============"+amount);
	    
	    
	    
	    //배송비 추가 (5만원 이상 주문 시 배송비 무료, 5만원이 안될경우 배송비 2,500)
	    if(amount < 50000) {
	    	amount = amount+2500;
	    }
	    
	    System.out.println("======배송비 추가 amount============"+amount);
	    
	    
	    
	    // 구매자 정보 불러오기(주소, 우편번호)
	    Order order = orderMapper.selectOrder(Order.builder().orderNo(Long.parseLong(map.get("orderNo").toString())).build());
	    String buyer_addr = order.getAddress1() + " " + order.getAddress2();
	    
	 
	    

		// => return 주문번호, 금액, 회원정보(이름, 이메일)
		Map<String, String> result = new HashMap<>();

		result.put("merchant_uid", map.get("orderNo")); // 주문번호
		result.put("name", productName);
		result.put("amount", String.valueOf(amount));
		result.put("buyer_email", member.getEmail()); // 구매자 e-mail
		result.put("buyer_name", member.getName()); // 구매자 이름
		result.put("buyer_tel", member.getPhone()); // 구매자 전화번호
		result.put("buyer_addr", buyer_addr);
		result.put("buyer_postcode", order.getZipcode());

		return result;

	}

	// 결제완료된 정보 처리
	@PostMapping("/payment_success")
	@ResponseBody
	public String insertPayment(@RequestBody Map<String, String> map, Model model, Authentication authentication) {
		// 결제완료 정보입력
		Map<String, String> result = new HashMap<>();

		result.put("uid", map.get("uid"));
		result.put("order", map.get("orderNo"));
		result.put("amount", map.get("amount"));
		result.put("method", map.get("method"));
		
		System.out.println("========================결과확인===========================");
		System.out.println(result);

		// payment 객체, 데이터 삽입
		Payment payment = new Payment();
		payment.setOrder(Order.builder().orderNo(Long.parseLong(map.get("orderNo"))).build());
		payment.setAmount(Integer.parseInt(map.get("amount")));
		payment.setUid(map.get("uid"));
		payment.setMethod(map.get("method"));
		payment.setSort("결제");
		
		System.out.println("================payment 객체확인===============");
		System.out.println(payment);
		
		paymentMapper.insertPayment(payment);
		
		System.out.println("===============데이터삽입 결과 확인=============");
		System.out.println(paymentMapper.insertPayment(payment));
		
		
		
		
		// 주문상태 결제완료로 변경하기
		
		 Order order = new Order();
		 
		 order.setOrderNo(Long.parseLong(map.get("orderNo").toString()));
		 order.setStatus("결제완료");
		 
		 orderMapper.updateOrder(order);
		

		
        
        
        
		
		// 장바구니 비우기
		//로그인 정보 확인
		CustomUser user = (CustomUser) authentication.getPrincipal();
		Member member = user.getMember();
		
		System.out.println("================로그인 정보 확인====================");
		System.out.println(member.getUsername());
		String memberName = member.getUsername();
		
		
		
		
		
		//옵션넘버 받아오기
		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(Order.builder().orderNo(Long.parseLong(map.get("orderNo").toString())).build());

		List<OrderItem> orderItemList = orderItemMapper.orderItemList(orderItem);
		System.out.println("================주문번호에 따른 주문상품 리스트 받아오기====================");
		System.out.println(orderItemList);
		
		// 옵션넘버구해서 리스트에 담기
		List<Map<String, String>> optionList = new ArrayList<Map<String, String>>();
		Map<String, String> optionMap = new HashMap<String, String>();
		for(int i = 0; i < orderItemList.size(); i++) {
			
			String orderItemOptionNo = Long.toString(orderItemList.get(i).getOption().getOptionNo());
			
			log.info(optionList.toString());
			
			optionMap.put("optionNo", orderItemOptionNo);
			
			optionList.add(optionMap);
		}
		
		
		log.info("+++++++++++++++++++++++optionList+++++++++++++++++++++++++++++++++++");
		log.info(optionList.toString());
		
		log.info("+++++++++++++++++++++++optionMap+++++++++++++++++++++++++++++++++++");
		log.info(optionMap.toString());
		
		
		cartMapper.selectCart(memberName);
		System.out.println("**********member정보확인*************");
		System.out.println(cartMapper.selectCart(memberName));
		log.info(cartMapper.selectCart(memberName).toString());
		
		
		System.out.println("**********cart 삭제됐는지 확인*************");
		cartService.deleteCart(optionMap, member);
		log.info(cartMapper.selectCart(memberName).toString());
		
		
		
		
		
		
		// 결제 완료 시 재고 감소
		// 결제 시 옵션번호따라 몇개씩 판매하였는지 수량 세기
		// 위의 판매 수량을 재고에서 감소
		
		// orderItem으로 재고여부 확인 (optionNo)
		List<Long> optionNoList = new ArrayList<>();
		// list를 for문으로 돌려 옵션넘버 구하기
		for(int i = 0; i < orderItemList.size(); i++) {
					
			Long orderItemOptionNo = orderItemList.get(i).getOption().getOptionNo();
				
			log.info("=============optionNo 구하기====================");
			log.info(orderItemOptionNo.toString());
					
			optionNoList.add(orderItemOptionNo);
			log.info("=============option====================");
			log.info(optionList.toString());
		}
				
		log.info("=============for문 밖 option====================");
		log.info(optionList.toString());
		
		Map<String, Long> opt = new HashMap<String, Long>();
		List<Map<String, Long>> list = new ArrayList<Map<String,Long>>();
		
		Set<Long> set = new HashSet<Long>(optionNoList);
		for(Long optionNo : set) {
			
			int quantity = Collections.frequency(optionNoList, optionNo);
			System.out.println(optionNo + ":" + quantity);
			
			opt.put("optionNo", optionNo);
			opt.put("quantity", (long)quantity);
			
			list.add(opt);
		}
		
		log.info("*************Hashmap 검사*************");
		log.info(opt.toString());
		
		log.info("*************Hashmap list 검사*************");
		log.info(list.toString());
		
		
	    
		//해시맵에 담긴 옵션번호로 재고확인		

	    for (Map<String, Long> m : list) {


	         Option option = optionMapper.selectOptionDetail(Long.parseLong(m.get("optionNo").toString()));
	         
	         int quantity = Integer.parseInt(m.get("quantity").toString());
	         
	         //재고확인
	         Option stockCk = optionMapper.selectOption(option);
	         int stock = stockCk.getStock();
	         log.info(stockCk.toString());   
	         System.out.println("=========stock========"+stock);
	         System.out.println("=========quantity========"+quantity);
	         
	         log.info("==============option===============" + option);
	         
	         int realStock = stock-quantity;
	         
	         
	         // 재고감소 업데이트 할 객체 생성
	         Option optionStock = new Option();
	         optionStock.setOptionNo(option.getOptionNo());
	         optionStock.setStock(realStock);
	         
	         log.info("************재고감소 확인*****************");
	         System.out.println(realStock);
	         System.out.println(optionStock);
	         
	         optionMapper.updateOption(optionStock);
	         
	      }
	    
		return "";
		
	}

	
	
	// 결제완료 페이지
	@GetMapping("/payment_success")
	public String goPaymentSuccess(){
		
		log.info("******************** // ***********************");
		return "/user/mypage/success";
	}

}
