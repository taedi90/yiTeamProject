package kr.or.yi.teamProject.product.controller;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.product.dto.Question;
import kr.or.yi.teamProject.product.service.QuestionService;
import kr.or.yi.teamProject.security.dto.CustomUser;
import kr.or.yi.teamProject.user.dto.Member;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
public class QuestionController {

    @Setter(onMethod_ = @Autowired)
    QuestionService questionService;

    // 질문작성
    @PostMapping("/item/question")
    public CommonResult postQuestion(@RequestBody Question question, Authentication authentication){
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Member member = customUser.getMember();

        question.setMember(member);

        return questionService.insertQuestion(question);
    }

    // 답변하기
    @PutMapping("/item/question")
    public CommonResult putQuestion(@RequestBody Question question, Authentication authentication){
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        Member member = customUser.getMember();

        question.setAnswerId(member.getUsername());

        return questionService.updateQuestion(question);
    }

    // 질문 삭제
    @DeleteMapping("/item/question")
    public CommonResult deleteQuestion(@RequestBody Question question){
        return questionService.deleteQuestion(String.valueOf(question.getQuestionNo()));
    }

    @GetMapping("/item/question")
    public Pager getQuestion(Pager pager) {
        return questionService.selectQuestionList(pager);
    }

}
