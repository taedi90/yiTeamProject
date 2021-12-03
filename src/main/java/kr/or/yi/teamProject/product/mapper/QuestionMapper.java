package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.product.dto.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int insertQuestion(Question question);
    int updateQuestion(Question question);
    int deleteQuestion(String questionNo);

    Question selectQuestion(String questionNo);

    Pager getInfoForPaging(Pager pager);
    List<Question> selectQuestionList(Pager pager);
}
