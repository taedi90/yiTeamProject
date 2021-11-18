package kr.or.yi.teamProject.product.service;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;

import java.util.List;

public interface ItemService {
    CommonResult readItem();

    CommonResult createItem(Item item);
    CommonResult updateItem(Item item);
    CommonResult deleteItem(int itemNo);
    
    Pager getInfoForPaging(Pager pager);
    Pager readItemForManage(Pager pager);
    List<String> getImagePath();
}
