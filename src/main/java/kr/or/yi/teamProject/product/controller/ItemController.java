package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.mapper.ItemMapper;
import kr.or.yi.teamProject.product.service.ItemService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemMapper itemMapper;

    @Setter(onMethod_ = @Autowired)
    ItemService itemService;

    //아이템 조회
    @GetMapping
    public void getItem(){

    }

    //아이템 생성
    @PostMapping
    public void PostItem(){
        //db에 아이템 번호 추가
        //이미지 폴더 부여?

    }

    //아이템 수정
    @PutMapping
    public CommonResult putItem(@RequestBody Item item){
//        log.info(item.toString());
        CommonResult commonResult = itemService.updateItem(item);

        return commonResult;
    }

    //아이템 삭제
    @DeleteMapping
    public void deleteItem(@RequestBody List<Item> list){
        list.forEach(item ->
                itemMapper.deleteItem(item));
    }

}
