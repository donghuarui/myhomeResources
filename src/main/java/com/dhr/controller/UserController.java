package com.dhr.controller;

import com.dhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.dhr.entity.User;

/**
 * Class description
 *
 *
 * @version        $version$, $date$, 18/12/15
 * @author         donghuarui.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /** Field description */
    @Autowired
    private UserService userService;


    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    @RequestMapping(value = "changeOne")
    public Integer changeOne(User user) {
        return userService.changeOne(user);
    }

    /**
     * 根据id删除一个用户
     *
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "deleteOne")
    public String deleteOne(String id) {
        System.err.print(id);
        userService.delete(id);
        return "0";
    }

    /**
     * 根据id查找一个用户
     *
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(value = "findOne")
    public User findOne(String id) {
        return userService.findOne(id);
    }

    /**
     * 登录
     *
     *
     * @param user
     *
     * @return
     */
    @RequestMapping(value = "login")
    public User login(User user) {
        return userService.login(user);
    }

    /**
     * 注册
     *
     *
     * @param user
     *
     * @return
     */
    @RequestMapping(value = "register")
    public User register(User user) {
        return userService.register(user);
    }

    /**
     * 分页查询用户
     *
     *
     *
     * @return
     */
    @RequestMapping("getAll")
    public Page<User> getAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum-1,pageSize);
        Page<User> page =  userService.getAll(pageable);
        return page;
    }
}

