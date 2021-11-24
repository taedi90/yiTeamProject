package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.service.ItemService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Setter(onMethod_ = @Autowired)
    ItemService itemService;

    @GetMapping("/detail")
    public String getDetail(Model model, @RequestParam("itemNo") int itemNo){

        CommonResult result = itemService.readItem(itemNo);

        if(result.isSuccess()){
            model.addAttribute("result", result.getObject());
        }

        return "product/product";
    }
}
