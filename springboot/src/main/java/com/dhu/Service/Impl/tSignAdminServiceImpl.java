package com.dhu.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhu.component.WebSocketServer;
import com.dhu.pojo.tSign;
import com.dhu.pojo.tSignAdmin;
import com.dhu.mapper.tSignAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dhu.Service.tSignAdminService;
import com.dhu.mapper.tSignAdminMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 14:20
 */
@Service
public class tSignAdminServiceImpl extends ServiceImpl<tSignAdminMapper, tSignAdmin> implements tSignAdminService{
    @Autowired
    private tSignAdminMapper tsignAdminMapper;
    @Autowired
    private WebSocketServer server;

    @Override
    public boolean adminAdd(tSignAdmin ts) {
        return save(ts);
    }


    @Override
    public Long getNumber(String uid) {
        QueryWrapper<tSignAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("initiator_id", uid);
        return count(queryWrapper);
    }

    @Override
    public List<tSignAdmin> getAll(String adminId, Integer pageNumber, Integer currentPage) {
        QueryWrapper<tSignAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("initiator_id", adminId);
        Page<tSignAdmin> page = new Page<>(currentPage, pageNumber);
        tsignAdminMapper.selectPage(page, queryWrapper);
        return page.getRecords();
    }

    @Override
    public Integer deleteSign(String uid, String id) {
        QueryWrapper<tSignAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("initiator_id", uid);
        queryWrapper.eq("id", id);
        return tsignAdminMapper.delete(queryWrapper);
    }

    @Override
    public boolean modify(String uid, String id, String activity, Date startTime, Date endTime) {
        QueryWrapper<tSignAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("initiator_id", uid);
        queryWrapper.eq("id", id);
        return update(queryWrapper);
    }

    @Override
    public List<tSignAdmin> getSignId() {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        QueryWrapper<tSignAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("start_time", date).ge("end_time", date);
        return list(queryWrapper);
    }

    @Override
    public void sendMessage(String message) {
        server.sendMessageToTeams(message);
    }
}
