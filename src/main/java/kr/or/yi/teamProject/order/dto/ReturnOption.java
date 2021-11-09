package kr.or.yi.teamProject.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnOption {
	private int returnOptionCode;	//반품코드
	private String reason;			//반품사유
	private boolean returnFree;		//배송비면제
	private String comment;			//비고
}
