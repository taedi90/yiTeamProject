package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.product.dto.Item;
import kr.or.yi.teamProject.product.dto.ItemPager;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    int updateItem(Item item);
    int deleteItem(Item item);  //on delete cascade

    Item selectItem(int itemNo);
    ArrayList<Item> selectItemList();

    ItemPager getInfoForPaging(ItemPager pager);

    List<Item> selectItemListForMain();
    List<Item> selectItemListForManage(ItemPager pager);
    List<String> getImagePath();
}
