package kr.or.yi.teamProject.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 공통 부분 컨트롤러
 *
 * @author taedi
 */

@Slf4j
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
//    public String orderTest(@RequestBody List<Map<String,Object>> list){
//        list.get(0).forEach((k,v)-> {log.info(k + (String) v);});
//        return "2021112610101099999";
//    }
//
//    @GetMapping("/order")
//    @ResponseBody
//    public String orderTest2(@RequestParam("orderNo")String orderNo){
//        return orderNo;
//    }

}

