package kr.or.yi.teamProject.user.dto;

import kr.or.yi.teamProject.common.dto.Pager;
import kr.or.yi.teamProject.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberPager extends Pager {
    private String social;
    private String confirm;
    private String name;
    private String username;
    private String email;
    private String authority;
}
