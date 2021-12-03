package kr.or.yi.teamProject.product.service.impl;


import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Question;
import kr.or.yi.teamProject.product.mapper.QuestionMapper;
import kr.or.yi.teamProject.product.service.QuestionService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Setter(onMethod_ = @Autowired)
    QuestionMapper questionMapper;

    @Override
    public CommonResult insertQuestion(Question question) {
        int res = questionMapper.insertQuestion(question);
        if (res == 1) {
            return CommonResult.SUCCESS;
        }
        return CommonResult.FAILURE;
    }

    @Override
    public CommonResult updateQuestion(Question question) {
        int res = questionMapper.updateQuestion(question);
        if (res == 1) {
            return CommonResult.SUCCESS;
        }
        return CommonResult.FAILURE;
    }

    @Override
    public CommonResult deleteQuestion(String questionNo) {
        int res = questionMapper.deleteQuestion(questionNo);
        if (res == 1) {
            return CommonResult.SUCCESS;
        }
        return CommonResult.FAILURE;
    }

    @Override
    public Question selectQuestion(String questionNo) {
        return questionMapper.selectQuestion(questionNo);
    }

    @Override
    public Pager selectQuestionList(Pager pager) {

        pager = questionMapper.getInfoForPaging(pager);

        pager.setRecords(questionMapper.selectQuestionList(pager));

        return pager;
    }
}
