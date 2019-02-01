package com.dhr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *  user实体
 *
 * @author donghuarui.
 * @version $version$, $date$, 19/01/07
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column(nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 授权码
     */
    @Column(nullable = false)
    private String perms;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", perms='" + perms + '\'' +
                '}';
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method description
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPerms() {
        return perms;
    }

    /**
     * Method description
     *
     * @param perms
     */
    public void setPerms(String perms) {
        this.perms = perms;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method description
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
