package kr.or.yi.teamProject.product.service.impl;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.mapper.CategoryMapper;
import kr.or.yi.teamProject.product.mapper.ItemMapper;
import kr.or.yi.teamProject.product.mapper.OptionMapper;
import kr.or.yi.teamProject.product.mapper.ProductInfoAnnounceMapper;
import kr.or.yi.teamProject.product.service.ProductService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Setter(onMethod_ = @Autowired)
    ItemMapper itemMapper;

    @Setter(onMethod_ = @Autowired)
    CategoryMapper categoryMapper;

    @Setter(onMethod_ = @Autowired)
    OptionMapper optionMapper;

    @Setter(onMethod_ = @Autowired)
    ProductInfoAnnounceMapper piaMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult createProduct(Item item) throws Exception {

        //값이 제대로 들어왔는지 확인
        //(상품 필수정보)
        //(상품 상세정보 없으면 hide -> on)
        //(옵션은 1개 이상 있어야 함)
        //할인 시작일 보다 종료일이 빠르거나 같으면 안됨


        //정보 제공고시 없을경우 추가

        //상품 생성
        int res = itemMapper.insertItem(item);
        if(res != 1){
            throw new Exception();
        }

        //옵션 생성
        item.getOptions().forEach(o -> {
            //여기서 추가
            //Item tempItem = Item.builder().itemNo(item.getItemNo()).build();
            o.setItem(item);
            optionMapper.insertOption(o);
        });




        return null;
    }
}
