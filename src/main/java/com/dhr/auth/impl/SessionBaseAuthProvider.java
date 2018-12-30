package com.dhr.auth.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.dhr.auth.AuthProvider;
import org.springframework.stereotype.Repository;

/**
 * Class description
 *
 *
 * @version        Enter version here..., 18/12/15
 * @author         donghuarui
 */
@Repository
public class SessionBaseAuthProvider implements AuthProvider {

    /** Field description */
    private HttpSession httpSession;

    /** Field description */
    @Value("${auth.session.userId:userId}")
    private String userIdSessionName;

    @Override
    public String getCurrentUserId() {
        Object userId = httpSession.getAttribute(userIdSessionName);
        return (userId == null)
               ? null
               : (String) userId;
    }
}
