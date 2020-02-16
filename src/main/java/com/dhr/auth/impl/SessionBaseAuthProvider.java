package com.dhr.auth.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dhr.auth.AuthProvider;
import org.springframework.stereotype.Repository;

/**
 * Class description
 *
 * @author donghuarui
 * @version Enter version here..., 18/12/15
 */
@Repository
public class SessionBaseAuthProvider implements AuthProvider {

    /**
     * Field description
     */

    private HttpSession httpSession;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * Field description
     */
    @Value("${auth.session.userId:userId}")
    private String userIdSessionName;

    @Value("${auth.token}")
    private String token;

    @Override
    public String getCurrentUserId() {
        //httpServletRequest.getHeader("Authentication-Token").toString()
//        Object userId = httpSession.getAttribute(userIdSessionName);
        Object userId = httpServletRequest.getHeader(token).toString();
        return (userId == null)
                ? null
                : (String) userId;
    }
}
