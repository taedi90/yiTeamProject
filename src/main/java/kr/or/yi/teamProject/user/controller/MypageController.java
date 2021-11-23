package kr.or.yi.teamProject.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@PreAuthorize("hasRole('USER')")
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	//마이페이지 메인
	@GetMapping
	public String goMypage(HttpServletRequest request){
	
		if(request.isUserInRole("ROLE_USER")) {
            log.info("이미 로그인 했을 경우 마이페이지로 이동");
           
		return "user/mypage";
		
		}
		
		log.info("로그인하지 않았을 경우 로그인페이지로 이동");
		return "user/login";	
	}
	
	
}
