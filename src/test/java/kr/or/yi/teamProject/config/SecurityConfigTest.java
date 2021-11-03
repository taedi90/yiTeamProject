package kr.or.yi.teamProject.config;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class SecurityConfigTest extends TestCase {

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void passwordEncoderTest(){
        String str = "test";
        log.info(encoder.encode(str));
    }

}