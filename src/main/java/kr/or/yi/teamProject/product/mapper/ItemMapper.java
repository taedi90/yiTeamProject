package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.product.dto.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    int updateItem(Item item);
    int deleteItem(Item item);

    Item selectItem(Item item);
    ArrayList<Item> selectItemList();
}
