package kr.or.yi.teamProject.user.controller;

import javax.servlet.http.HttpServletRequest;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	//마이페이지 메인
	@GetMapping
	public String goMypage(Model model) {

		model.addAttribute("url", "mypage/main.jsp");
//		model.addAttribute("result", pager);

		return "user/mypage";
	}

	//회원정보 변경
	@GetMapping(params = {"section=edit"})
	public String getEdit(Model model, Authentication authentication) {

		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();

		model.addAttribute("url", "mypage/edit.jsp");
		model.addAttribute("result", member);

		return "user/mypage";
	}
	
	
	
}
