package kr.or.yi.teamProject.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 택배사 전달 용 클래스
 *
 * @author taedi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carrier {
    private int carrierNo; //택배사 코드
    private String name; //택배사 명
    private String bizNo; //사업자 명
}
