package kr.or.yi.teamProject.user.mapper;

import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.dto.MemberPager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    Member selectMember(Member member);

    int insertNormalMember(Member member);
    int insertSocialMember(Member member);
    int updateMember(Member member);

    MemberPager getInfoForPaging(MemberPager pager);

    List<Member> selectMemberListForManage(MemberPager pager);
    List<Member> selectMemberListForAdmin(MemberPager pager);
    List<Member> selectNonManagerList(String username);

    int deactivateMember(Member member);
}
