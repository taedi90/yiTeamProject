package kr.or.yi.teamProject.product.service;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;

public interface ProductService {
    CommonResult createProduct(Item item) throws Exception;
}
