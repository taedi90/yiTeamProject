package kr.or.yi.teamProject.user.mapper;

import kr.or.yi.teamProject.user.dto.PrivacyAgreement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrivacyAgreementMapper {
    int insertPrivacyAgreement(PrivacyAgreement privacyAgreement);
}
