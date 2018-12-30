package com.dhr.config;

import java.util.ArrayList;
import java.util.List;

import com.dhr.interceptor.LoginInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.dhr.util.MyHandlerMethodReturnValueHandler;

/**
 * 配置静态资源
 * @version        $version$, $date$, 18/12/28
 * @author         donghuarui.
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public MyHandlerMethodReturnValueHandler myHandlerMethodReturnValueHandler() {
        return new MyHandlerMethodReturnValueHandler();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RequestMappingHandlerAdapter handlerAdapter =
            applicationContext.getBean(RequestMappingHandlerAdapter.class);
        List<HandlerMethodReturnValueHandler> handlers       = new ArrayList<HandlerMethodReturnValueHandler>();
        handlers.add(this.myHandlerMethodReturnValueHandler());
        handlers.addAll(handlerAdapter.getReturnValueHandlers());
        handlerAdapter.setReturnValueHandlers(handlers);
    }
    @Bean
    public LoginInterceptor getAuthInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(
                getAuthInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/login");
        super.addInterceptors(registry);
    }
}
