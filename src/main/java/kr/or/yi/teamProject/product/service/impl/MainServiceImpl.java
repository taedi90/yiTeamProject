package kr.or.yi.teamProject.product.service.impl;


import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.mapper.ItemMapper;
import kr.or.yi.teamProject.product.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    ItemMapper itemMapper;


    @Override
    public CommonResult getMainPage() {
        //숨김처리 안된 아이템
        Item searchOption = Item.builder()
                .hide(false)
                .build();

        //재고 없는 아이템 숨기기

        List<Item> items = itemMapper.selectItemListForMain();

        if (items != null) {
            CommonResult commonResult = CommonResult.SUCCESS;
            commonResult.setObject(items);
            return commonResult;
        }

        return null;
    }
}
