package cn.itcast.consumer.client.fallback;

import cn.itcast.consumer.client.UserClient;
import cn.itcast.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User findById(Long id) {
        User user=new User();
        user.setId(id);
        user.setName("用户查询异常!");
        return user;
    }
}
