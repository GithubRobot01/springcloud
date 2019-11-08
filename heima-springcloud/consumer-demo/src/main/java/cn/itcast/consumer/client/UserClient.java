package cn.itcast.consumer.client;

import cn.itcast.consumer.client.fallback.UserClientFallback;
import cn.itcast.consumer.config.FeignConfig;
import cn.itcast.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",fallback = UserClientFallback.class,
configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
