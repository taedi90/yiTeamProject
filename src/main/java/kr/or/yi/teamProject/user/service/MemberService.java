package kr.or.yi.teamProject.user.service;

import kr.or.yi.teamProject.user.dto.Member;
import kr.or.yi.teamProject.user.enums.RegisterResult;

public interface MemberService {

    RegisterResult insertNormalMember(Member member);

}
