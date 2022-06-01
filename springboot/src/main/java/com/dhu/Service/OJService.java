package com.dhu.Service;

import com.dhu.config.Result;
import com.dhu.pojo.User;
import com.dhu.utils.TwoTuple;
import io.swagger.annotations.ApiParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/29 20:47
 */
public interface OJService {
    TwoTuple<HashMap<String, Integer>, Integer> getLuoguInformation(HttpServletRequest request, String id);
}
