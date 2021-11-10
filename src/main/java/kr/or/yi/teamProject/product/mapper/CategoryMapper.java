package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.product.dto.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CategoryMapper {
    int insertCategory(Category category);
    int deleteCategory(Category category);
    int updateCategory(Category category);
    ArrayList<Category> selectCategoryList();
    Category selectCategory(Category category);
}
