package kr.or.yi.teamProject.user.service;

import kr.or.yi.teamProject.user.dto.Cart;
import kr.or.yi.teamProject.user.dto.Member;

import java.util.List;
import java.util.Map;

public interface CartService {
    List<Cart> getCart(List<Map<String, String>> webCart, Member member);

    void updateCart(Map<String, String> webItem, Member member);

    void deleteCart(Map<String, String> webItem, Member member);
}
