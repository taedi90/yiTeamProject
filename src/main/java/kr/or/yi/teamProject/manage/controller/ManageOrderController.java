package kr.or.yi.teamProject.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.order.dto.Order;
import kr.or.yi.teamProject.order.dto.OrderDetail;
import kr.or.yi.teamProject.order.dto.OrderItem;
import kr.or.yi.teamProject.order.mapper.OrderDetailMapper;
import kr.or.yi.teamProject.order.service.OrderDetailService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
//manager 또는 admin 만 접근 가능
//@PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
//@PreAuthorize("hasRole('ROLE_MANAGER')")
@RequestMapping("/manage")
public class ManageOrderController {
	

    @Setter(onMethod_ = @Autowired)
    OrderDetailService orderDetailService;
    
    @Setter(onMethod_ = @Autowired)
    OrderDetailMapper orderDetailMapper;
	
    //주문 관리 페이지
    @GetMapping(params = {"section=order","func=list"})
    public String getProductList(Pager pager, Model model) {

        log.info(pager.toString());

        pager = orderDetailService.selectOrderList(pager);

        log.info(pager.toString());

        model.addAttribute("url", "content/order/order-list.jsp");
        model.addAttribute("result", pager);

        return "manage/manage";
    }
    
    //주문 상세 페이지
    @GetMapping(params = {"section=order","func=detail"})
    public String getProductDetail(@RequestParam("orderNo") Long orderNo, Model model) {
    	
    	Order order= Order.builder().orderNo(orderNo).build();

        Order orderList = orderDetailMapper.selectDetailOrder(order);

        model.addAttribute("result", orderList);
        model.addAttribute("url", "content/order/order-detail.jsp");

        return "manage/manage";
    }

	
	

}
