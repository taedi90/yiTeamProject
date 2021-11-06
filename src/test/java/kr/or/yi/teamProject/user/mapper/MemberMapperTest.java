package kr.or.yi.teamProject.user.mapper;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import kr.or.yi.teamProject.user.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class MemberMapperTest extends TestCase {

    @Autowired
    private MemberMapper mapper;

    @Test
    public void testRead(){
        Member dto = mapper.selectMember("test");

        log.info(dto.toString());

    }


}