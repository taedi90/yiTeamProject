package kr.or.yi.teamProject.product.mapper;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.product.dto.Category;
import kr.or.yi.teamProject.product.dto.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
//단위 테스트 이후 db rollback
@Transactional
public class ItemMapperTest extends TestCase {

    @Autowired
    ItemMapper mapper;

    //테스트용 아이템 객체 생성
    Category category = Category.builder().categoryNo(1).build();
    private Item item = Item.builder()
            .category(category)
            .name("코트")
            .price(15000)
            .title("온라인 특가 코트")
            .text("상품 상세 설명")
            .build();


    //클래스 시작 시 1회만 시행
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        log.info("test_start");
    }

    //클래스 종료 시 1회만 시행
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        log.info("test_end");
    }

    //각 테스트 시작 시 마다 실행
    @Before
    public void setUp() throws Exception {
        //테스트 용 인서트 문
        mapper.insertItem(item);
    }

    //각 테스트 종료 시 마다 실행
    @After
    public void tearDown() throws Exception {
        //결과 출력
        mapper.selectItemList();
    }

    @Ignore("인서트문은 매번 시행하므로 제외 함")
    @Test
    public void insertItem() {
        log.info("=== 아이템 추가 ===");

        int result = mapper.insertItem(item);

        assertEquals(1, result);
    }

    @Test
    public void updateItem() {
        log.info("=== 아이템 수정 ===");

        item.setName("코오오오오트");
        int result = mapper.updateItem(item);

        assertEquals(1, result);
    }

    @Test
    public void deleteItem() {
        log.info("=== 아이템 삭제 ===");

        int result = mapper.deleteItem(item);

        assertEquals(1, result);
    }

    @Test
    public void selectItem() {
        log.info("=== 아이템 선택 ===");

        Item result = mapper.selectItem(item);

        assertNotNull(result);
    }

    @Ignore("매 테스트 종료마다 리스트 출력하므로 제외 함")
    @Test
    public void selectItemList() {
        log.info("=== 아이템 리스트 ===");

        ArrayList<Item> result = mapper.selectItemList();

        assertTrue(result.size() > 0);
    }
}