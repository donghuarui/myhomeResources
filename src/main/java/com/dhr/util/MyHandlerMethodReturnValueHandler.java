package com.dhr.util;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;

import com.dhr.entity.ResponseInfo;

/**
 * Class description
 * * 对controller返回的数据统一封装为ResponseInfo，注意：
 * 1、controller异常由spring mvc异常机制处理，会跳过该处理器
 * 2、该处理器仅处理包含@RestController、@ResponseBody注解的控制器
 *
 * @version        Enter version here..., 18/12/15
 * @author         Enter your name here...
 */
public class MyHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest)
            throws Exception {
        ResponseInfo responseInfo = new ResponseInfo();

        if (returnValue instanceof ResponseInfo) {
            responseInfo = (ResponseInfo) returnValue;
        } else {
            responseInfo.setData(returnValue);
        }

        // 标识请求是否已经在该方法内完成处理
        mavContainer.setRequestHandled(true);

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getWriter().write(JSON.toJSONString(responseInfo));
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Class<?> controllerClass = returnType.getContainingClass();

        returnType.getMethodAnnotation(ResponseBody.class);

        return controllerClass.isAnnotationPresent(RestController.class)
               || controllerClass.isAnnotationPresent(ResponseBody.class)
               || (returnType.getMethodAnnotation(ResponseBody.class) != null);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
