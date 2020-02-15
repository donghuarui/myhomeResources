package com.dhr.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import com.dhr.entity.User;

/**
 * user模块
* @author         donghuarui.
 */
public interface UserService extends BaseService<User> {

    /**
     *  修改一个用户
     * @param user
     * @return
     */
    Integer changeOne(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     *注册
     * @param user
     * @return
     */
    Map<String,Object> register(User user);

    /**
     * 条件查询1
     * @param user
     * @return
     */
    List<User> tiaojian(User user);

    /**
     * 条件查询2
     * @param user
     * @return
     */
    List<User> tiaojian2(User user);

    /**
     * 条件查询3
     * @param user
     * @return
     */
    List<User> tiaojian3(User user);

    /**
     * 条件查询4
     * 多条件 动态查询
     * @param user
     * @return
     */
    List<User> tiaojian4(User user);

    /**
     * 条件查询5
     * @param user
     * @return
     */
    List<User> tiaojian5(User user);


    /**
     * 分页加排序查询所有
     * @param pageNum
     * @param pageSize
     * @param sortParams
     * @return
     */
    Page<User> getAll(Integer pageNum,Integer pageSize,String sortParams);

    /**
     * 查看用户名是否重复
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
