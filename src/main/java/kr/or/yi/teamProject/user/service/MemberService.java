package kr.or.yi.teamProject.user.service;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.dto.MemberPager;
import kr.or.yi.teamProject.user.enums.RegisterResult;
import kr.or.yi.teamProject.user.enums.SendConfirmMailResult;

import java.util.List;


public interface MemberService {

    RegisterResult insertNormalMember(Member member);

    SendConfirmMailResult sendConfirmMail(Member member);

    CommonResult updateMember(Member member);

    MemberPager selectMemberListForManage(MemberPager pager);

    Member selectMember(Member member);

    MemberPager selectMemberListForAdmin(MemberPager pager);
    List<Member> selectNonManagerList(String username);

}
