package kr.or.yi.teamProject.product.service;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Question;

import java.util.List;

public interface QuestionService {
    CommonResult insertQuestion(Question question);
    CommonResult updateQuestion(Question question);
    CommonResult deleteQuestion(String questionNo);

    Question selectQuestion(String questionNo);

    Pager selectQuestionList(Pager pager);
}
