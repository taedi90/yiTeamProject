package kr.or.yi.teamProject.product.service;

import kr.or.yi.teamProject.common.enums.CommonResult;

import java.util.List;
import java.util.Map;

public interface ThumbService {
    CommonResult uploadThumb(Map<String, Object>  map);

    List<Map<String,Object>> uploadImages(Map<String, Object> map);
}
