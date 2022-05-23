package com.dhu.Service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/11 23:57
 */
public interface MailService {
    void sendMail(String to, String subject, String content);
}
