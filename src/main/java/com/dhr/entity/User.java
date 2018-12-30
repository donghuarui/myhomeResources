package com.dhr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class description
 *
 *
 * @version        1.0.0, 18/12/15
 * @author         donghaurui
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /** Field description */
    @Column(nullable = false)
    private String username;

    /** Field description */
    @Column(nullable = false)
    private String password;

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method description
     *
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method description
     *
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
