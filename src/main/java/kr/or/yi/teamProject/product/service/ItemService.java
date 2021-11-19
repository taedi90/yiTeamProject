package kr.or.yi.teamProject.product.service;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.ItemPager;

import java.util.List;

public interface ItemService {
    CommonResult readItem(int itemNo);

    CommonResult createItem(Item item);
    CommonResult updateItem(Item item);
    CommonResult deleteItem(int itemNo);

    ItemPager getInfoForPaging(ItemPager pager);
    ItemPager readItemForManage(ItemPager pager);
    List<String> getImagePath();
}
