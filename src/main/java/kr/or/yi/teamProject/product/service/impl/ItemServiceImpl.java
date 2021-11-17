package kr.or.yi.teamProject.product.service.impl;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.product.mapper.ItemMapper;
import kr.or.yi.teamProject.product.mapper.OptionMapper;
import kr.or.yi.teamProject.product.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    OptionMapper optionMapper;

    @Override
    public CommonResult readItem() {
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

    @Override
    @Transactional
    public CommonResult createItem(Item item) {
        int result = itemMapper.insertItem(item);

        //상품 생성
        if (result == 1){

            //옵션 생성
            Option option = Option.builder().item(
                    Item.builder().itemNo(item.getItemNo()).build()
                ).build();

            int dbResult = optionMapper.insertOption(option);

            if(dbResult == 1) {
                List<Option> list = new ArrayList<Option>();
                list.add(option);
                item.setOptions(list);

                CommonResult commonResult = CommonResult.SUCCESS;
                commonResult.setObject(item);
                return commonResult;
            }

        }

        return null;
    }

    @Override
    public CommonResult updateItem(Item item) {
        return null;
    }

    @Override
    public CommonResult deleteItem(int itemNo) {
        return null;
    }

    @Override
    public Pager getInfoForPaging(Pager pager) {

        pager = itemMapper.getInfoForPaging(pager);

        return pager;
    }

    @Override
    public Pager readItemForManage(Pager pager) {
        List<Item> items = itemMapper.selectItemListForManage(pager);

        if (items != null) {
            pager.setRecords(items);
            return pager;
        }
        return null;
    }
}
