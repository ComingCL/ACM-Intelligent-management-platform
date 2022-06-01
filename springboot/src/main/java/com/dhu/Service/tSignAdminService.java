package com.dhu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhu.pojo.tSignAdmin;

import java.util.Date;
import java.util.List;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 14:20
 */
public interface tSignAdminService extends IService<tSignAdmin> {
    /**
     * 管理员添加签到信息
     * @param ts 实参
     * @return 返回是否添加成功
     */
    boolean adminAdd(tSignAdmin ts);
    /**
     * 管理员查看自己发布的签到信息
     * @param uId 管理员id
     * @param pageNumber 每页显示数据条数
     * @param currentPage 当前是第几页
     * @return 返回当前管理员发布的签到信息
     */
    List<tSignAdmin> getAll(String uId, Integer pageNumber, Integer currentPage);

    /**
     * 查询当前管理员发出的签到数量
     * @param uid 当前管理员编号
     * @return 返回数量
     */
    Long getNumber(String uid);
    /**
     * 管理员根据id删除已发布的某个签到
     * @param uid 管理员id
     * @param id 签到id
     * @return 返回是否签到成功
     */
    Integer deleteSign(String uid, String id);
    /**
     * 管理员修改签到信息
     * @param uid 管理员id
     * @param id 签到id
     * @param activity 活动名
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 返回签到是否成功
     */
    boolean modify(String uid, String id, String activity, Date startTime, Date endTime);
    /**
     * @return 查询当前用户活动签到信息
     */
    List<tSignAdmin> getSignId();
}
