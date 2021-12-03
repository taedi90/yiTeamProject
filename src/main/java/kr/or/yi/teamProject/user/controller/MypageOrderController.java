package kr.or.yi.teamProject.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.yi.teamProject.order.dto.OrderDetail;
import kr.or.yi.teamProject.order.mapper.OrderDetailMapper;
import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.service.MemberService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageOrderController {
    
    @Setter(onMethod_ = @Autowired)
	OrderDetailMapper orderDetailMapper;
	
	//마이페이지 주문내역
	@GetMapping(params = {"section=order","func=detail"})
	public String getEdit(Model model, Authentication authentication) {

		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();
		
		List<OrderDetail> orderList = orderDetailMapper.orderDetailSelectList(member);
		
		log.info(orderList.toString());

		model.addAttribute("url", "mypage/order.jsp");
		model.addAttribute("result", orderList);

		return "user/mypage";
	}
	
	
	
}
