package com.dhr.service;

import com.dhr.entity.User;
import com.dhr.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserServiceImpl userService;

    @Test
    public void tiaojian1(){
        User user = new User();
        user.setUsername("ezreal");
        List<User> users = userService.tiaojian(user);
        for(User newuser : users){
            System.out.print(newuser);
        }
        System.err.print(users.size());
    }
}
