package kr.or.yi.teamProject.user.mapper;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.user.dto.Auth;
import kr.or.yi.teamProject.user.enums.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class AuthMapperTest extends TestCase {

    @Autowired
    AuthMapper authMapper;

    @Test
    public void insertAuthTest() {


        Integer res = authMapper.insertAuth(
                    Auth.builder()
                        .username("oauth_taedi90@gmail.com")
                        .authority(Role.ROLE_UNCERTIFIED)
                        .build()
                    );

        log.info(res.toString());

        assertNotNull(res);


    }
}