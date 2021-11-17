package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.product.mapper.OptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item/option")
public class OptionController {

    @Autowired
    OptionMapper optionMapper;

    //옵션 조회
    @GetMapping
    public void getOption(){

    }

    //옵션 생성
    @PostMapping
    public CommonResult PostOption(@RequestBody Option option){
        int result = optionMapper.insertOption(option);

        if(result == 1){
            CommonResult commonResult = CommonResult.SUCCESS;
            commonResult.setObject(option);
            return commonResult;
        }

        return CommonResult.FAILURE;
    }

    //옵션 수정
    @PutMapping
    public void putOption(){

    }

    //옵션 삭제
    @DeleteMapping
    public CommonResult deleteOption(@RequestBody Option option){
        int result = optionMapper.deleteOption(option);

        if(result == 1){
            CommonResult commonResult = CommonResult.SUCCESS;
//            commonResult.setObject();
            return commonResult;
        }

        return CommonResult.FAILURE;
    }
}
