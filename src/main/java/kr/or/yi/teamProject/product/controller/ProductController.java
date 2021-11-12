package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.service.ProductService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/manage/product")
public class ProductController {

    @Setter(onMethod_ = @Autowired)
    ProductService productService;

    @PostMapping("put")
    public void createProduct(@RequestBody Item item) throws Exception{
        log.info(item.toString());

        productService.createProduct(item);

    }
}
