package com.dhr.controller;

import com.alibaba.fastjson.JSON;
import com.dhr.entity.ResponseInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController {

    @ExceptionHandler
    public void exp(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setErrorCode(ResponseInfo.ERROR_CODE_MAPPING_FAILED);
        responseInfo.setErrorMsg(ex.getMessage());
        response.getWriter().write(JSON.toJSONString(responseInfo));
    }
}