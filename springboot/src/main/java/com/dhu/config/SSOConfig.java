package com.dhu.config;

import com.dhu.interceptor.AdminHandlerInterceptor;
import com.dhu.interceptor.LoginHandlerInterceptor;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: ComingLiu
 * @Date: 2022/5/30 21:28
 */
@Configuration
public class SSOConfig implements WebMvcConfigurer {
    @Autowired
    LoginHandlerInterceptor loginHandlerInterceptor;
    @Autowired
    AdminHandlerInterceptor adminHandlerInterceptor;
    @Bean
    public TomcatContextCustomizer sameSiteCookiesConfig() {
        return context -> {
            final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
            context.setCookieProcessor(cookieProcessor);
        };
    }

//    以后再写吧
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/Login/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/swagger/**")
                .excludePathPatterns("/Mail/**")
                .excludePathPatterns("/Index/**")
                .excludePathPatterns("/send/**")
                .excludePathPatterns("");
        registry.addInterceptor(adminHandlerInterceptor).addPathPatterns("/Admin/**");
    }
}
