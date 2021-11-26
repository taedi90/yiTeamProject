package kr.or.yi.teamProject.user.service.impl;

import kr.or.yi.teamProject.product.dto.Option;
import kr.or.yi.teamProject.product.mapper.OptionMapper;
import kr.or.yi.teamProject.user.dto.Cart;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.mapper.CartMapper;
import kr.or.yi.teamProject.user.service.CartService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Setter(onMethod_ =  @Autowired)
    OptionMapper optionMapper;

    @Setter(onMethod_ =  @Autowired)
    CartMapper cartMapper;

    @Override
    @Transactional
    public List<Cart> getCart(List<Map<String, String>> webCart, Member member) {
        List<Cart> result = new ArrayList<>();

        if(member != null){
            //db 조회
            result = cartMapper.selectCart(member.getUsername());
        }

        if(webCart != null){

            for(int i = 0; i < webCart.size(); ++i) {
                Map<String, String> cartItem = webCart.get(i);

                long optionNo = Long.parseLong(cartItem.get("optionNo"));
                int quantity = Integer.parseInt(cartItem.get("quantity"));

                Cart cart = Cart.builder()
                        .member(member)
                        .quantity(quantity)
                        .option(Option.builder().optionNo(optionNo).build())
                        .build();

                result.add(cart);
            }




            //db 결과와 합치기
        }

        //기존 db 지우기

        //새로 insert 하기

        // 가져오고

        // 지우고

        // 인서트하고

        // 품절은 stock 보고 판단

        return null;
    }
}
