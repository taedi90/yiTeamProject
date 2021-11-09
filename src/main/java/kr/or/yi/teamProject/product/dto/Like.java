package kr.or.yi.teamProject.product.dto;

import kr.or.yi.teamProject.user.dto.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 리뷰 좋아요 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    private Review review; //리뷰
    private Member member; //회원
    private Date regDate; //등록일자
}
