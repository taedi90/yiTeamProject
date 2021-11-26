package kr.or.yi.teamProject.product.mapper;

import kr.or.yi.teamProject.product.dto.Option;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionMapper {
    int insertOption(Option option);
    int updateOption(Option option);
    int deleteOption(Option option);
    Option selectOption(Option option);
    Option selectOptionDetail(Option option);
}
