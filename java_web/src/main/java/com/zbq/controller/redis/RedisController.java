package com.zbq.controller.redis;

import com.zbq.dao.mapper.UserRepository;
import com.zbq.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangboqing
 * @date 2018/4/2
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user=userRepository.findByUserName("aa");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }
}
