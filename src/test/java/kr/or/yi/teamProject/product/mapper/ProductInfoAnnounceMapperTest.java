package kr.or.yi.teamProject.product.mapper;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.product.dto.ProductInfoAnnounce;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductInfoAnnounceMapperTest extends TestCase {

    @Autowired
    private ProductInfoAnnounceMapper mapper;

    private final ProductInfoAnnounce pia = ProductInfoAnnounce.builder()
            .title("test_상품정보제공고시")
            .text("상품정보제공고시 본문")
            .build();
    private ProductInfoAnnounce modPia = ProductInfoAnnounce.builder()
            .title("mod_상품정보제공고시")
            .text("수정 본문")
            .build();

    private final String modTitle = "mod_상품정보제공고시";
    private final String modText = "수정 본문";


    private ArrayList<ProductInfoAnnounce> piaList;


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        log.info("종료 후 DB 내역 --->");
        selectProductInfoAnnounceList();
    }

    @Test
    public void _01_insertProductInfoAnnounce() {
        log.info("=== 상품정보제공고시 생성 ===");

        int result = mapper.insertProductInfoAnnounce(pia);

        assertEquals(1, result);
    }

    @Test
    public void _02_selectProductInfoAnnounce() {
        log.info("=== title로 조회 ===");

        ProductInfoAnnounce result = mapper.selectProductInfoAnnounce(pia);

        assertNotNull(result);
    }

    @Test
    public void _03_updateProductInfoAnnounce() {
        log.info("=== title, text 변경 ===");

        piaList = mapper.selectProductInfoAnnounceList();

        piaList.forEach(i -> {
            if(i.getTitle().equals(pia.getTitle())){
                modPia.setPiaNo(i.getPiaNo());
                int result = mapper.updateProductInfoAnnounce(modPia);
                assertEquals(1, result);
            }
        });

    }

    @Test
    public void _04_deleteProductInfoAnnounce() {
        log.info("=== 삭제 ===");

        piaList = mapper.selectProductInfoAnnounceList();

        piaList.forEach(i -> {
            if(i.getTitle().equals(modPia.getTitle())){
                int result = mapper.deleteProductInfoAnnounce(i);
                assertEquals(1, result);
            }
        });
    }






    public ArrayList<ProductInfoAnnounce> selectProductInfoAnnounceList() {
        return mapper.selectProductInfoAnnounceList();
    }
}