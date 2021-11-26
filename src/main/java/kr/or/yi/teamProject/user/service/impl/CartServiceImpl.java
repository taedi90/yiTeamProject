package kr.or.yi.teamProject.user.service.impl;

import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.product.mapper.OptionMapper;
import kr.or.yi.teamProject.user.dto.Cart;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.mapper.CartMapper;
import kr.or.yi.teamProject.user.service.CartService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Setter(onMethod_ = @Autowired)
    OptionMapper optionMapper;

    @Setter(onMethod_ = @Autowired)
    CartMapper cartMapper;

    @Override
    //@Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Cart> getCart(List<Map<String, String>> webCart, Member member) {
        List<Cart> cartItems = new ArrayList<>();

        if (webCart != null) {
            // cart 객체로 변환
            for (int i = 0; i < webCart.size(); ++i) {
                Map<String, String> webItem = webCart.get(i);

                long optionNo = Long.parseLong(webItem.get("optionNo"));
                int quantity = Integer.parseInt(webItem.get("quantity"));

                Option option = optionMapper.selectOptionDetail(optionNo);

                Cart cart = Cart.builder()
                        .member(member)
                        .quantity(quantity)
                        .option(option)
                        .build();

                cartItems.add(cart);
            }
        }

        if (member == null) {
            //비회원의 경우 카트 반환
            return cartItems;
        }

        //db 조회
        String username = member.getUsername();
        List<Cart> dbCart = cartMapper.selectCart(username);

        //웹 카트와 병합
        cartItems.addAll(cartMapper.selectCart(username));

        //중복 검사(중복 레코드 수량 합치기)
        boolean isModified = false;
        if(webCart != null && dbCart.size() > 0){
            for (int i = 0; i < cartItems.size(); i++) {
                for (int j = i + 1; j < cartItems.size(); j++) {
                    long optionI = cartItems.get(i).getOption().getOptionNo();
                    long optionJ = cartItems.get(j).getOption().getOptionNo();
                    if (optionI == optionJ) {
                        int quantity = cartItems.get(i).getQuantity() + cartItems.get(j).getQuantity();
                        cartItems.get(i).setQuantity(quantity);
                        cartItems.remove(j--);
                        isModified = true;
                    }
                }
            }
        }

        if(webCart != null || isModified == true) {
            //기존 db 지우기
            cartMapper.deleteCart(Cart.builder().member(member).build());

            //새로 insert 하기
            cartItems.forEach(c -> {cartMapper.insertCart(c);});

            //조회
            cartItems = cartMapper.selectCart(username);
        }

        // 품절은 stock 보고 판단

        return cartItems;
    }

    @Override
    public void updateCart(Map<String, String> webItem, Member member) {

        long optionNo = Long.parseLong(webItem.get("optionNo"));
        int quantity = Integer.parseInt(webItem.get("quantity"));

        Option option = Option.builder().optionNo(optionNo).build();

        Cart cart = Cart.builder()
                .member(member)
                .quantity(quantity)
                .option(option)
                .build();

        cartMapper.updateCart(cart);


    }

    @Override
    public void deleteCart(Map<String, String> webItem, Member member) {

        long optionNo = Long.parseLong(webItem.get("optionNo"));

        Option option = Option.builder().optionNo(optionNo).build();

        Cart cart = Cart.builder()
                .member(member)
                .option(option)
                .build();

        cartMapper.deleteCart(cart);
    }
}
