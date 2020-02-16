package com.dhr.repository;

import com.dhr.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends BaseRepository<User, String> {

    /**
     * 根据方法名
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 用问号的方式
     *
     * @param username
     * @return
     */
    @Query("select u from User u where u.username = ?1")
    User findUserByUsername(String username);

    @Query(value = "from User where username = ?1")
    User findUserByUsername1(String username);

    /**
     * 冒号 + @param 的形式
     *
     * @param username
     * @return
     */
    @Query("select u from User u where u.username = :username")
    User findUserByUsername2(@Param("username") String username);


    /**
     * like的时候 冒号 + @param形式
     *
     * @param username
     * @return
     */
    @Query("select u from User u where u.username like %:username%")
    User findUserByUsernameLike(@Param("username") String username);

    /**
     * like的时候  问号的形式
     *
     * @param username
     * @return
     */
    @Query("select u from User u where u.username like %?1%")
    User findUserByUsernameLike1(String username);

    /**
     * like的时候 ， 用原生的方式  nativeQuery = true
     *
     * @param username
     * @return
     */
    @Query(nativeQuery = true, value = "select u from user u where u.username like %?%")
    User findUserByUsernameLike2(String username);

    //以下是更新操作

    @Query("update User u set u.username = :username , u.valid = :valid , u.updaterId = :updaterId where u.id = :id")
    @Modifying
    Integer modifyUser(@Param("username") String username, @Param("valid") Character valid, @Param("id") String id, @Param("updaterId") String updaterId);
}
