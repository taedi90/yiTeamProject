package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.util.ImageUtil;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.service.ProductService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Slf4j
@Controller
@RequestMapping("/manage/product")
public class ProductController {

    @Setter(onMethod_ = @Autowired)
    ProductService productService;

    @PostMapping("put")
    public void createProduct(@RequestBody Item item) throws Exception{
        log.info(item.toString());

        productService.createProduct(item);

    }

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
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

}
