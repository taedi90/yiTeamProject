package kr.or.yi.teamProject.user.mapper;


import kr.or.yi.teamProject.user.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member selectMember(String username);
    int insertSocialMember(Member member);
}
