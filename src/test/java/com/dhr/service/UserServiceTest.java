package com.dhr.service;

import com.dhr.entity.User;
import com.dhr.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    /**
     * 单条件查询like
     */
    @Test
    public void tiaojian1() {
        User user = new User();
        user.setUsername("ezreal");
        List<User> users = userService.tiaojian(user);
        for (User newuser : users) {
            System.out.print(newuser.getId());
        }
        System.err.print(users.size());
    }

    /**
     * 单条件查询equal
     */
    @Test
    public void tiaojian2(){
        User user = new User();
        user.setUsername("ezreal");
        List<User> users = userService.tiaojian2(user);
        for (User newuser : users) {
            System.out.print(newuser);
        }
        System.err.print(users.size());
    }

    /**
     * 带?的查询
     */
    @Test
    public void findUserByUsername() {
        User user = userService.findUserByUsername("ezreal");
        System.err.print(user);
    }

    /**
     * 排序查询
     */
    @Test
    public void getAllBySort() {
        //如果多个添加的排序  用 Order order = new Order(Sort.Direction.ASC, "createTime") 然后放到Sort里面
        Sort sort = new Sort(Sort.Direction.ASC, "createTime");
        List<User> users = userService.getAll(sort);
        for(User user : users){
            System.err.print(user);
        }
    }
}
