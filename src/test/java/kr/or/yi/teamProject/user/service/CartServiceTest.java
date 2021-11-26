package kr.or.yi.teamProject.user.service;

import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.user.dto.Cart;
import kr.or.yi.teamProject.user.mapper.CartMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class CartServiceTest {

    @Setter(onMethod_ = @Autowired)
    CartMapper cartMapper;

    @Test
    public void getCart() {
        List<Cart> list = cartMapper.selectCart("root");
        log.info(String.valueOf(list.size()));
        list.forEach(e->log.info(e.toString()));
    }
}