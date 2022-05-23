package com.dhu.config;

import com.dhu.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Author: ComingLiu
 * Date: 2022/4/20 11:44
 */
@Configuration(proxyBeanMethods = false) // 告诉SpringBoot这是一个配置类 == 配置文件
//条件装配
//@ConditionalOnBean(name = "tom") 容器中有tom组件的时候下面的类才生效
//@ConditionalOnMissingBean(name = "tom") 容器中没有tom组件的时候下面的类才生效
//@ImportResource("classpath:beans.xml") 这可以允许导入Spring的配置文件的方式导入组件
public class Myconfig {

}
