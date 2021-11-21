package kr.or.yi.teamProject.user.service.impl;

import kr.or.yi.teamProject.common.enums.CommonResult;
import kr.or.yi.teamProject.user.dto.Auth;
import kr.or.yi.teamProject.user.mapper.AuthMapper;
import kr.or.yi.teamProject.user.service.AuthService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Setter(onMethod_ = @Autowired)
    AuthMapper authMapper;

    @Override
    public CommonResult insertAuth(Auth auth) {

        int res = authMapper.insertAuth(auth);

        if(res == 1){
            return CommonResult.SUCCESS;
        }

        return CommonResult.FAILURE;
    }

    @Override
    public CommonResult deleteAuth(Auth auth) {
        int res = authMapper.deleteAuth(auth);

        if(res == 1){
            return CommonResult.SUCCESS;
        }

        return CommonResult.FAILURE;
    }
}
