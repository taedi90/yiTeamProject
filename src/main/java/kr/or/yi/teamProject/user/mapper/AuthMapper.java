package kr.or.yi.teamProject.user.mapper;

import kr.or.yi.teamProject.user.dto.Auth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    int insertAuth(Auth auth);
    int deleteAuth(Auth auth);
}
