package kr.or.yi.teamProject.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 페이징 처리를 위한 dto 클래스
 */
@Data
@Builder
@AllArgsConstructor
public class Pager {
    //요청 값
    private int pageNo; //페이지 번호
    private int amount; //한 페이지에 표시할 레코드 수
    private String category; //카테고리
    private String keyword; //검색 키워드
    private String order; //정렬 순서

    //DB 조회 결과
    private int startRecord; // 페이지 시작 레코드(인덱스)
    private int totalRecords; //전체 레코드 수
    private int finalPageNo; //끝 페이지 번호

    //링크 영역
    private int linkCount; //한번에 표시할 페이지 링크 갯수
    private int startPage; //페이지 시작
    private int endPage; //페이지 종료

    private int prev; //이전
    private int next; //다음


    private Object records; //결과 객체

    public Pager() {
        this.pageNo = 1;
        this.amount = 10;
        this.order = "desc";
        this.linkCount = 10;
    }

}
