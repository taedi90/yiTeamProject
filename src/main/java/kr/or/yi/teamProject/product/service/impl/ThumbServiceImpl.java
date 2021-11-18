package kr.or.yi.teamProject.product.service.impl;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.common.util.ImageUtil;
import kr.or.yi.teamProject.common.util.RandomStringCreateUtil;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.service.ItemService;
import kr.or.yi.teamProject.product.service.ThumbService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ThumbServiceImpl implements ThumbService {

    @Setter(onMethod_ = @Autowired)
    ItemService itemService;

    @Override
    public CommonResult uploadThumb(Map map) {

        //전달 받은 내용
        Item item = (Item) map.get("item");
        MultipartFile thumb = (MultipartFile) map.get("thumb");


        //이미지 경로 있는지 확인, 없으면 db에 경로 입력
        String subPath = item.getImage();
        log.info(subPath);
        if(subPath == null || subPath.equals("")) {

            RandomStringCreateUtil randomStringCreateUtil = new RandomStringCreateUtil();

            //이미지 경로 insert 시도
            for(int i = 0; i < 30; i++){

                subPath = randomStringCreateUtil.getSecureRand();
                List<String> pathList = itemService.getImagePath();

                if(!pathList.contains(subPath)){

                    Item dto = new Item();
                    dto.setItemNo(item.getItemNo());
                    dto.setImage(subPath);

                    CommonResult result = itemService.updateItem(dto);
                    if(result.isSuccess()){
                        break;
                    }

                }
            }
        }

        //썸네일 생성
        if(subPath != null){
            ImageUtil imageUtil = new ImageUtil();
            boolean isThumbCreated = imageUtil.thumbResize(thumb, subPath);

            if(isThumbCreated) {

                CommonResult result = CommonResult.SUCCESS;
                result.setObject(subPath);
                return result;
            }
        }

        return CommonResult.FAILURE;
    }
}
