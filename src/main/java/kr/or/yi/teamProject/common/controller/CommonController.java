package kr.or.yi.teamProject.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 공통 부분 컨트롤러
 *
 * @author taedi
 */


@Controller
public class CommonController {
    @GetMapping("/access-denied")
    public String getAccessDenied(){
        return "error/access-denied";
    }

    // 주문 전 로그인 완료 시
    @GetMapping("/after-login")
    public String afterLogin(){
        return "order/after-login";
    }

    //order 테스트용
//    @PostMapping("/order")
//    @ResponseBody
//    public String orderTest(){
//        return "2021112610101099999";
//    }
//
//    @GetMapping("/order")
//    @ResponseBody
//    public String orderTest2(@RequestParam("orderNo")String orderNo){
//        return orderNo;
//    }

}

