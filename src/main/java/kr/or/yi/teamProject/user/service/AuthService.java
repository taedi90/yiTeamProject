package kr.or.yi.teamProject.user.service;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.user.dto.Auth;

public interface AuthService {
    CommonResult insertAuth(Auth auth);
    CommonResult deleteAuth(Auth auth);
}
