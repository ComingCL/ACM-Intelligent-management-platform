package com.dhu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 扫描mapper接口所在的包
@MapperScan("com.dhu.mapper")
public class AcmIntelligentManagementPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmIntelligentManagementPlatformApplication.class, args);
    }
}
