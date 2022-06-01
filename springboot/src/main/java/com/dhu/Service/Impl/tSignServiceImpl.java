package com.dhu.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhu.Service.tSignAdminService;
import com.dhu.Service.tSignService;
import com.dhu.mapper.tSignMapper;
import com.dhu.pojo.tSign;
import com.dhu.mapper.tSignMapper;
import com.dhu.pojo.tSignAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 14:20
 */
@Service
public class tSignServiceImpl extends ServiceImpl<tSignMapper, tSign> implements tSignService {
    @Autowired
    private tSignMapper tsignMapper;
    @Autowired
    private tSignAdminService tsAdminService;

    @Override
    public Integer userSign(String person, String uid, String sid) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        tSignAdmin ts = tsAdminService.getById(uid);
        QueryWrapper<tSign> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid", sid).eq("uid", uid);
        tSign signInfo = tsignMapper.selectOne(queryWrapper);
        if(signInfo != null) return 2;
        if(ts.getEndTime().before(date)){
            return 3;
        }
        if(save(new tSign(null, ts.getActivity(), date, person, sid, uid))) return 1;
        return 0;
    }
}
