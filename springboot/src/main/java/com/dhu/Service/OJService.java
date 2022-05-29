package com.dhu.Service;

import com.dhu.config.Result;
import io.swagger.annotations.ApiParam;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/29 20:47
 */
public interface OJService {
    Result<?> getLuoguInformation(String id);

}
