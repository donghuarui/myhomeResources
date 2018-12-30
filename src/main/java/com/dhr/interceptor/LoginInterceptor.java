package com.dhr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dhr.repository.redisutil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * @version        $version$, $date$, 18/12/28
 * @author         donghuarui.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    public RedisUtil redisUtil;

    private static final String USER_REDIS_SESSION = "user:redis:session:";

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e)
            throws Exception {}

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView)
            throws Exception {}

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        //System.err.print(USER_REDIS_SESSION+httpServletRequest.getHeader("Authentication-Token").toString());
        //System.err.print(redisUtil.hasKey("user:redis:session:402880e467c148550167c1ed327e0008"));
        //System.err.print(redisUtil.get("user:redis:session:402880e467c148550167c1ed327e0008"));
        System.err.print(redisUtil.get(USER_REDIS_SESSION+httpServletRequest.getHeader("Authentication-Token").toString()));
        System.err.print(redisUtil.hasKey(USER_REDIS_SESSION+httpServletRequest.getHeader("Authentication-Token").toString()));
        return redisUtil.hasKey(USER_REDIS_SESSION+httpServletRequest.getHeader("Authentication-Token").toString());
    }
}
