package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.product.dto.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    int updateItem(Item item);
    int deleteItem(Item item);  //on delete cascade

    ArrayList<Item> selectItem(Item item);
    ArrayList<Item> selectItemList();

    Pager getInfoForPaging(Pager pager);

    List<Item> selectItemListForMain();
    List<Item> selectItemListForManage(Pager pager);
    List<String> getImagePath();
}
