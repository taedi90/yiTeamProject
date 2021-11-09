package kr.or.yi.teamProject.manage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 관리자 페이지 관련 컨트롤러
 *
 * @author taedi
 */

@Slf4j
@Controller
public class ManageController {

    @GetMapping("/manage")
    public String getManage() {
        return "manage/manage";
    }
}
