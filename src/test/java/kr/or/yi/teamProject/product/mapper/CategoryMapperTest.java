package kr.or.yi.teamProject.product.mapper;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.product.dto.Category;
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
//테스트를 메서드 명 오름차순으로 실행
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//단위 테스트 이후 db rollback
@Transactional
public class CategoryMapperTest extends TestCase {

    @Autowired
    private CategoryMapper categoryMapper;

    private ArrayList<Category> categoryList;

    private static Category category = Category.builder()
            .title("test_category")
            .build();

    private static String modTitle;

    //테스트 전 1회 실행
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        //수정할 카테고리 명
        modTitle = "mod_category";

    }

    //매 테스트마다 실행
    @Before
    public void setUp() throws Exception {

        //테스트용 category 객체 생성
        category = Category.builder()
                .title("test_category")
                .build();

        categoryMapper.insertCategory(category);
    }

    //매 테스트 종료 시 마다 실행
    @After
    public void tearDown() throws Exception {


        log.info("=== 테스트 종료 ===");
        //categoryList = selectCategoryList();
        //categoryList.forEach(i -> log.info(i.toString()));
    }

    @Ignore("데이터 삽입은 매 케이스 실행시 진행")
    @Test
    public void _01_insertCategory() {
        log.info("=== 카테고리 생성 ===");
        int result = categoryMapper.insertCategory(category);
        log.info("성공 여부 - > " + result );
        log.info("입력 받은 열 번호 - > " + category.getCategoryNo());
        assertTrue(result > 0);
    }


    @Test
    public void _01_selectCategoryByNo() {
        log.info("=== no로 조회 ===");
        category.setTitle(null);
        Category result = categoryMapper.selectCategory(category);
        log.info("조회 결과 -> " + result.toString());
        assertNotNull(result);
    }

    @Test
    public void _02_selectCategoryByTitle() {
        log.info("=== title로 조회 ===");
        category.setCategoryNo(0);
        Category result = categoryMapper.selectCategory(category);
        log.info("조회 결과 -> " + result.toString());
        assertNotNull(result);
    }

    @Test
    public void _03_updateCategory() {
        log.info("=== 카테고리 명 변경 ===");

        category.setTitle(modTitle);
        int result = categoryMapper.updateCategory(category);
        assertTrue(result > 0);

    }

    @Test
    public void _04_deleteCategoryById() {
        log.info("=== 카테고리 번호로 삭제 ===");

        category.setTitle(null);
        int result = categoryMapper.deleteCategory(category);
        assertEquals(1, result);
    }


    public ArrayList<Category> selectCategoryList() {
        ArrayList<Category> categoryList = categoryMapper.selectCategoryList();

        return categoryList;
    }
}