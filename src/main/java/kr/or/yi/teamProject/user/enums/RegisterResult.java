package kr.or.yi.teamProject.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum RegisterResult {
    INPUT_ERROR(false, "올바른 값을 입력해주세요.", null),
    NULL_ERROR(false, "항목을 모두 기입해주세요.", null),
    DUPLICATE_ERROR(false, "이미 사용중인 아이디 입니다.", null),
    USERNAME_ERROR(false, "아이디는 영문, 숫자, '-', '_' 조합 4 ~ 12자 내외로 작성해 주시기 바랍니다.", null),
    PASSWORD_ERROR(false, "비밀번호는 영문, 숫자 조합 4 ~ 12자 내외로 작성해 주시기 바랍니다.", null),
    EMAIL_ERROR(false, "올바른 이메일 형식을 기입해 주세요.", null),
    PHONE_ERROR(false, "핸드폰 번호는 '-' 없이 11자리 숫자를 입력해주세요.", null),
    FAILURE(false, "가입에 실패하였습니다.", null),
    SUCCESS(true, "메일 인증을 완료하시면 가입이 완료 됩니다.", null);

    private final boolean isSuccess;
    private final String comment;
    private Cookie cookie;

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}
