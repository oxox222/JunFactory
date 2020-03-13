package com.example.sale.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: LoginInterceptor
 * @Description: 拦截器
 * @Author: PANLVZ
 * @Date: 2020-03-13
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        Object object = session.getAttribute("user");
        if (object == null) {
            throw new LoginException("请先登录");
        }
        return true;
    }
}
