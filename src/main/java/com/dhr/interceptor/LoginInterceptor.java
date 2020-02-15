package com.dhr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dhr.entity.ResponseInfo;
import com.dhr.repository.redisutil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录拦截器
 *
 * @author donghuarui.
 * @version $version$, $date$, 18/12/28
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    public RedisUtil redisUtil;

    private static final String USER_REDIS_SESSION = "user:redis:session:";

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        Boolean status = httpServletRequest.getHeader("Authentication-Token") == null || !redisUtil.hasKey(USER_REDIS_SESSION + httpServletRequest.getHeader("Authentication-Token").toString());
        if (status) {
            //重置response
            httpServletResponse.reset();
            httpServletResponse.setCharacterEncoding("UTF-8"); //设置编码格式
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = httpServletResponse.getWriter();
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setErrorCode(ResponseInfo.ERROR_CODE_MAPPING_FAILED);
            responseInfo.setErrorMsg("拦截器拦截，请登录");
            pw.write(JSON.toJSONString(responseInfo));
            pw.flush();
            pw.close();
            return false;
        }
        return true;
    }
}
