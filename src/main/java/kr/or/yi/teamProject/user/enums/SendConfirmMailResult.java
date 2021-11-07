package kr.or.yi.teamProject.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.Cookie;

@Getter
@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum SendConfirmMailResult {
    INVALID_ERROR(false, "아이디 또는 이메일이 일치하지 않습니다.", null),
    FAILURE(false, "전송에 실패하였습니다. 나중에 다시 시도하시기 바랍니다.", null),
    SUCCESS(true, "전송이 완료되었습니다.", null);


    private final boolean isSuccess;
    private final String comment;
    private Cookie cookie;

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}
