package kr.or.yi.teamProject.common.util;

import junit.framework.TestCase;
import kr.or.yi.teamProject.config.RootConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class MailUtilTest extends TestCase {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSendMail(){
        log.info("메일 발송 테스트");
        log.info(mailSender.toString());
        try {
            MailUtil sendMail = new MailUtil(mailSender);
            sendMail.setSubject("테스트 메일 발송");
            sendMail.setText(new StringBuffer().append("<h1>메일 발송 테스트</h1>")
                    .append("<p>잘 보이나요?.</p>")
                    .toString());
            sendMail.setFrom("no-reply@taedi.net", "발송용");
            sendMail.setTo("taedi90@gmail.com");
            sendMail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}