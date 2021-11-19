package kr.or.yi.teamProject.product.service.impl;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.ItemPager;
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
    public CommonResult readItem(int itemNo) {
        Item item = itemMapper.selectItem(itemNo);

        if (item != null) {
            CommonResult commonResult = CommonResult.SUCCESS;
            commonResult.setObject(item);
            return commonResult;
        }

        return null;
    }

    @Override
    @Transactional
    public CommonResult createItem(Item item){

        //상품 생성
        int result = itemMapper.insertItem(item);


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

        //등록 건의 경우 필수값 체크
        if(item.isPublish() == true){

            //필수값 누락은 저장 x
            if(item.getName() == null || item.getPrice() <= 0){
                return CommonResult.FAILURE;
            }

            //옵션은 배열 돌면서
            for(Option option : item.getOptions()) {
                if(option.getName() == null) {
                    return CommonResult.FAILURE;
                }
            }

            //상품 상세 누락은 hide => true
            if(item.getTitle() == null || item.getText() == null) {
                item.setHide(true);
            }

        }

        //아이템 업데이트
        int resItem = itemMapper.updateItem(item);
        if(resItem == 0) {
            return CommonResult.FAILURE;
        }

        //옵션 업데이트
        List<Option> options = item.getOptions();
        if(options != null) {
            for(Option option : item.getOptions()) {
                int resOption = optionMapper.updateOption(option);
                if(resOption == 0 ){
                    return CommonResult.FAILURE;
                }
            }
        }

        return CommonResult.SUCCESS;
    }

    @Override
    public CommonResult deleteItem(int itemNo) {
        return null;
    }

    @Override
    public ItemPager getInfoForPaging(ItemPager pager) {

        pager = itemMapper.getInfoForPaging(pager);

        return pager;
    }

    @Override
    public ItemPager readItemForManage(ItemPager pager) {
        List<Item> items = itemMapper.selectItemListForManage(pager);

        if (items != null) {
            pager.setRecords(items);
            return pager;
        }
        return null;
    }

    @Override
    public List<String> getImagePath() {

        return itemMapper.getImagePath();
    }
}
