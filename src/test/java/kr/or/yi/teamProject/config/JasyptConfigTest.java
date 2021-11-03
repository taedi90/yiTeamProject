package kr.or.yi.teamProject.config;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class JasyptConfigTest extends TestCase {

    @Autowired
    private JasyptConfig jasyptConfig;

    @Resource
    private Environment env;

    List<String> arr = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        // 암호화 할 값 입력
        arr.add("jdbc:mysql://taesoo.ga:20111/project?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void stringEncryptor() {
        StringEncryptor encryptor = jasyptConfig.stringEncryptor(env);

        String enc = null;
        String dec = null;

        for(String plain : arr){
            enc = encryptor.encrypt(plain);
            dec = encryptor.decrypt(enc);

            log.info("enc -> " + enc);
            log.info("dec -> " + dec);

            assertEquals(plain, dec);
        }
    }
}