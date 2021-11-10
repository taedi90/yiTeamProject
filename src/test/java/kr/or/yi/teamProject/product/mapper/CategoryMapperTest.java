package kr.or.yi.teamProject.product.mapper;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.product.dto.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
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
public class CategoryMapperTest extends TestCase {

    @Autowired
    private CategoryMapper categoryMapper;

    private Category category;

    private ArrayList<Category> categoryList;

    private String modTitle;

    //매 테스트마다 실행 - before, beforeEach
    @Before
    public void setUp() throws Exception {
        //테스트용 category 객체 생성
        category = Category.builder()
                .title("test_category")
                .build();

        //수정할 카테고리 명
        modTitle = "mod_category";
    }

    //매 테스트 종료 시 마다 실행
    @After
    public void tearDown() throws Exception {


        log.info("종료 후 DB 내역 --->");
        categoryList = selectCategoryList();
        //categoryList.forEach(i -> log.info(i.toString()));
    }

    @Test
    public void _01_insertCategory() {
        log.info("=== 카테고리 생성 ===");
        int result = categoryMapper.insertCategory(category);
        assertEquals(1, result);
    }

    @Test
    public void _02_selectCategoryByTitle() {
        log.info("=== title로 조회 ===");
        Category result = categoryMapper.selectCategory(category);
        log.info("조회 결과 -> " + result.toString());
        assertNotNull(result);
    }

    @Test
    public void _03_updateCategory() {
        log.info("=== 카테고리 명 변경 ===");

        categoryList = selectCategoryList();

        categoryList.forEach(i -> {
            if (i.getTitle().equals(category.getTitle())) {
                //카테고리 명 변경
                i.setTitle(modTitle);
                int result = categoryMapper.updateCategory(i);
                assertEquals(1, result);
            }
        });
    }

    @Test
    public void _04_deleteCategoryById() {
        log.info("=== 카테고리 번호로 삭제 ===");
        categoryList = selectCategoryList();
        categoryList.forEach(i -> {
            if (i.getTitle().equals(modTitle)) {
                int result = categoryMapper.deleteCategory(i);
                assertEquals(1, result);
            }

        });
    }


    public ArrayList<Category> selectCategoryList() {
        ArrayList<Category> categoryList = categoryMapper.selectCategoryList();

        return categoryList;
    }
}