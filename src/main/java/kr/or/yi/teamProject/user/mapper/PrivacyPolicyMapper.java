package kr.or.yi.teamProject.user.mapper;

import kr.or.yi.teamProject.user.dto.PrivacyPolicy;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrivacyPolicyMapper {
    PrivacyPolicy selectPrivacyPolicy();
}
