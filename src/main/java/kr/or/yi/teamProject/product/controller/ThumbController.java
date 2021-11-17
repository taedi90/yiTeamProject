package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.product.dto.Item;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ThumbController {

    //썸네일 업로드
    @PostMapping("/manage/product/thumb")
    public void postThumb(@RequestPart(value = "item")Item item,
                          @RequestPart(value = "thumb")MultipartFile thumb){
        //게시글 번호,



    }
}
