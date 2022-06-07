package com.dhu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhu.pojo.tSignAdmin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 14:35
 */
@Repository
public interface tSignAdminMapper extends BaseMapper<tSignAdmin> {
    List<tSignAdmin> getSignId(@Param("date") Date date);
}
