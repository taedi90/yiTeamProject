package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.util.ImageUtil;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.service.ProductService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

@Slf4j
@Controller
@RequestMapping("/manage/product")
public class ProductController {

    @Setter(onMethod_ = @Autowired)
    ProductService productService;

    //리스트 보기
    @GetMapping("/list")
    public void getList(){

    }

    //생성 페이지
    @GetMapping("/create")
    public void getNewItem(){

    }

    //수정 페이지
    @GetMapping("/update")
    public void getEditor(){

    }




    //신규 생성
    @PostMapping("/item")
    public void createProduct(@RequestBody Item item) throws Exception{
        log.info(item.toString());

        productService.createProduct(item);

    }

    //수정
    @PutMapping("/item")
    public void putItem() {

    }

    //삭제
    @DeleteMapping("/item")



    @PostMapping("/upload-img")
    public void uploadFormPost(MultipartFile[] uploadFile,
                               Model model,
                               HttpServletRequest request) {
        for (MultipartFile multipartFile : uploadFile) {
            log.info("-------------------------------------");
            log.info("Upload File Name: " +multipartFile.getOriginalFilename());
            log.info("Upload File Size: " +multipartFile.getSize());

            //확장자 확인
            multipartFile.getOriginalFilename();

            log.info(ImageUtil.UPLOAD_PATH);

            File saveFile = new File(ImageUtil.UPLOAD_PATH, multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(saveFile);

                BufferedImage img = ImageIO.read(multipartFile.getInputStream());

                ImageUtil imageUtil = new ImageUtil();
                //imageUtil.resizeGif(saveFile);


            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

//    @PostMapping("/manage/product/image")
//    public

}
