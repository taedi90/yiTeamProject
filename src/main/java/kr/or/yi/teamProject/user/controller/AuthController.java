package kr.or.yi.teamProject.user.controller;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.user.dto.Auth;
import kr.or.yi.teamProject.user.enums.Role;
import kr.or.yi.teamProject.user.service.AuthService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 권한 관련 컨트롤러
 *
 * @author taedi
 */
@Slf4j
@Controller
@Secured("ROLE_ADMIN")
public class AuthController {

    @Setter(onMethod_ =  {@Autowired})
    AuthService authService;

    @PostMapping("/admin/auth")
    @ResponseBody
    public CommonResult addManager(@RequestBody Auth auth){
        auth.setAuthority(Role.ROLE_MANAGER);
        return authService.insertAuth(auth);
    }

    @DeleteMapping("/admin/auth")
    @ResponseBody
    public CommonResult deleteManager(@RequestBody Auth auth){
        auth.setAuthority(Role.ROLE_MANAGER);
        return authService.deleteAuth(auth);
    }

}
