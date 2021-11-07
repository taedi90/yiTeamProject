package kr.or.yi.teamProject.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum CommonResult {
    FAILURE(false, "요청을 실패하였습니다. 나중에 다시 시도하시기 바랍니다.", null),
    SUCCESS(true, "요청에 성공하였습니다.", null);

    private final boolean isSuccess;
    private String comment;
    private Object object;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
