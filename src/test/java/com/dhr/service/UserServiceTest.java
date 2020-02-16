package com.dhr.service;

import com.dhr.entity.User;
import org.springframework.data.domain.Sort;
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
    private UserService userService;

    /**
     * 单条件查询like
     */
    @Test
    public void tiaojian1() {
        User user = new User();
        user.setUsername("ezreal");
        List<User> users = userService.tiaojian(user);
        users.forEach(u -> System.out.println(u.getId()));
        System.err.print(users.size());
    }

    /**
     * 单条件查询equal
     */
    @Test
    public void tiaojian2() {
        User user = new User();
        user.setUsername("ezreal");
        List<User> users = userService.tiaojian2(user);
        for (User newuser : users) {
            System.out.print(newuser);
        }
        System.err.print(users.size());
    }

    /**
     * 单条件查询equal
     */
    @Test
    public void tiaojian4() {
        User user = new User();
        user.setUsername("ez");
        user.setValid('1');
        List<User> users = userService.tiaojian4(user);
        users.forEach(System.out::println);
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
        Sort sort = new Sort(Sort.Direction.ASC, "updateTime");
        List<User> users = userService.getAll(sort);
        for (User user : users) {
            System.err.print(user);
        }
    }

    @Test
    public void modifyUser() {
        User user = new User();
        user.setId("4028826e704777e50170477866100000");
        user.setUsername("donghauruiddd");
        user.setValid('1');
        Integer status = userService.changeOne(user);
        System.err.println(status);
    }
}
