package com.dhr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dhr.entity.User;
import org.springframework.data.domain.Sort;

/**
 * Interface description
 *
* @author         donghuarui.
 */
public interface UserService extends BaseService<User> {

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    Integer changeOne(User user);

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    User login(User user);

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    User register(User user);

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    List<User> tiaojian(User user);

    List<User> tiaojian2(User user);

    /**
     * Method description
     *
     *
     * @param page
     *
     * @return
     */
    Page<User> getAll(Pageable page);

    List<User> getAll(Sort sort);


    /**
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
