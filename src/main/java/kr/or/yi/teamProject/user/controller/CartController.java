package kr.or.yi.teamProject.user.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Cart;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.service.CartService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {
	
	//장바구니
	@GetMapping
	public String goCart(){
		return "user/cart";
	}

	@Setter(onMethod_ = @Autowired)
	CartService cartService;

	@PostMapping
	@ResponseBody
	public List<Cart> postCart(HttpServletRequest request,
						   @RequestBody List<Map<String, String>> webCart,
						   Authentication authentication){


		CustomUser user = (CustomUser) authentication.getPrincipal();
		Member member = user.getMember();

		List<Cart> result = cartService.getCart(webCart, member);


		return result;
	}
}
