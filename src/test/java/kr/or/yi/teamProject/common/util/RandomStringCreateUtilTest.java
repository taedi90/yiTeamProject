package kr.or.yi.teamProject.common.util;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class RandomStringCreateUtilTest extends TestCase {

    private RandomStringCreateUtil rscu;

    @Before
    public void setUp() throws Exception {
        rscu = new RandomStringCreateUtil();
        // 사이즈 설정
        rscu.setSize(15);
        rscu.setCharSet(new char[] {'1', 'a', 'k'});
    }

    @Test
    public void getSecureRand() {

        String result = rscu.getSecureRand();
        log.info(result);

        assertNotNull(result);
    }
}