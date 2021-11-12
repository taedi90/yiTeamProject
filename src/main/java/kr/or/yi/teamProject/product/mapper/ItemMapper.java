package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.product.dto.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    int updateItem(Item item);
    int deleteItem(Item item);

    ArrayList<Item> selectItem(Item item);
    ArrayList<Item> selectItemList();

    List<Item> selectItemListForMain();
}
