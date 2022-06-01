package com.dhu.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhu.pojo.tSign;

/**
 * @Author: ComingLiu
 * @Date: 2022/6/1 14:20
 */
public interface tSignService extends IService<tSign> {
    /**
     * 用户签到
     * @param person 用户名称
     * @param uid 用户id
     * @param sid 签到id
     * @return 返回1表示签到成功, 返回2表示已经签到过了, 返回3表示签到已过期, 返回0表示数据库插入失败
     */
    Integer userSign(String person, String uid, String sid);
}
