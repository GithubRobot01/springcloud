package cn.itcast.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    Logger.Level feignLoggerLevel(){
        //记录所有请求和响应的明细，包括头信息、请求体、元数据        
        return Logger.Level.FULL;
    }
}
