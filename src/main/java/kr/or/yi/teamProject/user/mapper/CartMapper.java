package kr.or.yi.teamProject.user.mapper;

import kr.or.yi.teamProject.user.dto.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> selectCart(String username);
    int deleteCart(Cart cart);
    int updateCart(Cart cart);
    int insertCart(Cart cart);
}
