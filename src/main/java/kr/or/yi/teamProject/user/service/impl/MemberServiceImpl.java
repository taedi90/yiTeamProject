package kr.or.yi.teamProject.user.service.impl;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.common.util.MailUtil;
import kr.or.yi.teamProject.common.util.RandomStringCreateUtil;
import kr.or.yi.teamProject.user.dto.Auth;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.enums.RegisterResult;
import kr.or.yi.teamProject.user.enums.Role;
import kr.or.yi.teamProject.user.enums.SendConfirmMailResult;
import kr.or.yi.teamProject.user.mapper.AuthMapper;
import kr.or.yi.teamProject.user.mapper.MemberMapper;
import kr.or.yi.teamProject.user.service.MemberService;
import lombok.Setter;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberServiceImpl implements MemberService {

    @Setter(onMethod_ =  {@Autowired})
    MemberMapper memberMapper;

    @Setter(onMethod_ =  {@Autowired})
    AuthMapper authMapper;

    @Setter(onMethod_ =  {@Autowired})
    PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    StringEncryptor encryptor;


    // 일반 회원 가입
    @Override
    public RegisterResult insertNormalMember(Member member) {

        // 필수항목 입력 확인
        if(member.getUsername().equals("") ||
                member.getName().equals("") ||
                member.getPassword().equals("") ||
                member.getEmail().equals("") ||
                member.getPhone().equals("")
        ) {
            return RegisterResult.NULL_ERROR;
        }


        // 아이디 형식 확인
        if(checkUsername(member.getUsername()) != true){
            return RegisterResult.USERNAME_ERROR;
        }

        // 패스워드 형식 확인
        if(checkPassword(member.getPassword()) != true){
            return RegisterResult.PASSWORD_ERROR;
        }

        // 이메일 형식 확인
        if(checkEmail(member.getEmail()) != true){
            return RegisterResult.EMAIL_ERROR;
        }

        // 전화번호 형식 확인
        if(checkPhone(member.getPhone()) != true){
            return RegisterResult.PHONE_ERROR;
        }

        // 아이디 중복 확인
        Member dbResult = memberMapper.selectMember(
                Member.builder().username(member.getUsername()).build()
        );
        if (dbResult != null){
            return RegisterResult.DUPLICATE_ERROR;
        }

        // 패스워드 변환
        member.setPassword(
                passwordEncoder.encode(member.getPassword())
        );

        // 회원 가입
        int insertNormalMemberResult = memberMapper.insertNormalMember(member);



        if (insertNormalMemberResult == 1) {

            // ROLE_USER 부여
            Auth auth = Auth.builder()
                    .username(member.getUsername())
                    .authority(Role.ROLE_USER)
                    .build();
            int insertAuthResult = authMapper.insertAuth(auth);

            // 인증 메일 발송
            SendConfirmMailResult sendConfirmMailResult = sendConfirmMail(member);

            if(insertAuthResult == 1 && sendConfirmMailResult.isSuccess() == true){

                RegisterResult result = RegisterResult.SUCCESS;
                result.setCookie(sendConfirmMailResult.getCookie());

                return result;
            }

        }

        return RegisterResult.FAILURE;

    }

    // 확인 이메일 발송
    @Override
    public SendConfirmMailResult sendConfirmMail(Member member) {

        // request 확인
        if(member.getUsername() == null
        || member.getEmail() == null) {
            return SendConfirmMailResult.INVALID_ERROR;
        }

        // db 조회(회원 없음, 이메일 인증 완료)
        Member dbResult = memberMapper.selectMember(member);
        if(dbResult == null || dbResult.isEmailConfirm() == true){
            return SendConfirmMailResult.INVALID_ERROR;
        }

        //인증키 생성
        String authKey = new RandomStringCreateUtil().getSecureRand();
        String username = member.getUsername();


        //이메일 발송
        boolean result = sendEmail(member, authKey);


        //결과 확인
        if (result == true) {

            String encValue = encryptor.encrypt(username + authKey);

            //쿠키 생성
            Cookie emailConfirmCookie = new Cookie("emailConfirm", encValue);
            //emailConfirmCookie.setMaxAge(30 * 60); //초 단위
            emailConfirmCookie.setMaxAge(-1); //브라우저 종료 시 까지
            emailConfirmCookie.setHttpOnly(true);
            emailConfirmCookie.setSecure(true);

            SendConfirmMailResult res = SendConfirmMailResult.SUCCESS;
            res.setCookie(emailConfirmCookie);

            return res;
        }


        return SendConfirmMailResult.FAILURE;
    }

    @Override
    public CommonResult updateMember(Member member) {
        int res = memberMapper.updateMember(member);

        if(res == 1){
            return CommonResult.SUCCESS;
        }

        return CommonResult.FAILURE;
    }


    public boolean sendEmail(Member member, String authKey) {
        try {
            MailUtil sendMail = new MailUtil(mailSender);
            sendMail.setSubject("[yiTeamProject] 이메일 인증 메일 발송");
            sendMail.setText(new StringBuffer().append("<h1>이메일 인증</h1>")
                    .append("<p>가입해 주셔서 감사합니다.</p>")
                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                    .append("<a href='http://localhost:8080/shop/email-confirm?id=")
                    .append(member.getUsername())
                    .append("&confirm=")
                    .append(authKey)
                    .append("' target='_blenk'>이메일 인증 확인</a>")
                    .toString());
            sendMail.setFrom("no-reply@taedi.net", "발송용");
            sendMail.setTo(member.getEmail());
            sendMail.send();

            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }



    public boolean checkEmail(String str) {
        String regEx = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
            return true;
        }

        return false;
    }

    public boolean checkPhone(String str) {
        String regEx = "^01\\d{9}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
            return true;
        }

        return false;
    }

    public boolean checkPassword(String str) {
        String regEx = "^[A-Za-z0-9]{4,12}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
            return true;
        }

        return false;
    }

    public boolean checkUsername(String str) {
        String regEx = "^[A-Za-z0-9-_]{4,12}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);

        if(matcher.find()) {
            return true;
        }

        return false;
    }

}

