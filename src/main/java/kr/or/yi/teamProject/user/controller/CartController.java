package kr.or.yi.teamProject.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {
	
	//장바구니
	@GetMapping
	public String goCart(HttpServletRequest request){
	
		if(request.isUserInRole("ROLE_USER")) {
	           log.info("이미 로그인 했을 경우 장바구니로 이동");
	          
		return "user/cart";
		
		}
			
		log.info("로그인하지 않았을 경우 로그인페이지로 이동");
		return "user/login";	
	}
}
