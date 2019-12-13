package cn.itcast.consumer.controller;

import cn.itcast.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    //@HystrixCommand(fallbackMethod = "findByIdFallback")
    @HystrixCommand
    public String findById(@PathVariable Long id){
        if (id==1){
            throw new RuntimeException("繁忙");
        }

        /*String url = "http://localhost:9091/user/"+id;
        //获取eureka中注册的user-service的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance serviceInstance = instances.get(0);

        url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id;
        */
        String url = "http://user-service/user/"+id;
        return restTemplate.getForObject(url,String.class);
    }

    public String findByIdFallback(Long id){
        log.error("查询用户信息失败!id:{}",id);
        return "网络拥挤,请稍后再试!";
    }

    public String defaultFallback(){
        return "默认提示!";
    }
}
