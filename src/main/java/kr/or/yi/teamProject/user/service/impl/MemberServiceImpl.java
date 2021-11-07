package kr.or.yi.teamProject.user.service.impl;

import kr.or.yi.teamProject.user.dto.Auth;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.enums.RegisterResult;
import kr.or.yi.teamProject.user.enums.Role;
import kr.or.yi.teamProject.user.mapper.AuthMapper;
import kr.or.yi.teamProject.user.mapper.MemberMapper;
import kr.or.yi.teamProject.user.service.MemberService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    // 일반 회원 가입
    @Override
    public RegisterResult insertNormalMember(Member member) {

        // 필수항목 입력 확인
        if(member.getUsername() == null ||
                member.getName() == null ||
                member.getPassword() == null ||
                member.getEmail() == null ||
                member.getPhone() == null
        ) {
            return RegisterResult.NULL_ERROR;
        }

        // 아이디 중복 확인
        Member dto = memberMapper.selectMember(member.getUsername());
        if (dto != null){
            return RegisterResult.DUPLICATE_ERROR;
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



        // 패스워드 변환
        member.setPassword(
                passwordEncoder.encode(member.getPassword())
        );

        // 회원 가입
        int res = memberMapper.insertNormalMember(member);

        // ROLE_USER 부여
        Auth auth = Auth.builder()
                .username(member.getUsername())
                .authority(Role.ROLE_USER)
                .build();

        authMapper.insertAuth(auth);

        if (res == 1) {
            return RegisterResult.SUCCESS;
        }


        return RegisterResult.FAILURE;

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

