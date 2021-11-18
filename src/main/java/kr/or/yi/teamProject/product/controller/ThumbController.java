package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.service.ThumbService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ThumbController {

    @Setter(onMethod_ = @Autowired)
    ThumbService thumbService;


    //썸네일 업로드
    @PostMapping("/item/thumb")
    public CommonResult postThumb(@RequestPart(value = "item")Item item,
                        @RequestPart(value = "thumb")MultipartFile thumb){


        log.info(item.toString());

        log.info(thumb.getOriginalFilename());

        Map<String, Object> map = new HashMap<>();
        map.put("item", item);
        map.put("thumb", thumb);

        CommonResult commonResult = thumbService.uploadThumb(map);

        return commonResult;
    }
}
