package com.dhu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
// 扫描mapper接口所在的包`
@MapperScan("com.dhu.mapper")
public class AcmIntelligentManagementPlatformApplication {

    public static void main(String[] args) {
        /**
         * 两个参数: 一是应用入口的类SpringApplication, 二是run方法的执行
         *         1. 推断应用的类型是普通的项目还是web项目
         *         2. 查找并加载所有可用初始化器, 设置到initializers属性中
         *         3. 找出所有的应用程序监听器, 设置到listeners属性中
         *         4. 推断并设置main方法的定义类, 找到运行的主类
         */
        ConfigurableApplicationContext run = SpringApplication.run(AcmIntelligentManagementPlatformApplication.class, args);
//        String[] names = run.getBeanDefinitionNames();
//        boolean haha = run.containsBean("haha");
//        System.out.println(haha);
    }
}
