package kr.or.yi.teamProject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 포인트 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private  Member member;
    private int point;
    private long orderItemNo;
    private long reviewNo;
    private Date regDate;
    private Date expDate;
}
