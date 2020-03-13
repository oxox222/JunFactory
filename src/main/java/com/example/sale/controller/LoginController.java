package com.example.sale.controller;

import com.example.sale.model.TUser;
import com.example.sale.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: LoginController
 * @Description: 登录接口
 * @Author: PANLVZ
 * @Date: 2020-03-13
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String Login(@RequestBody TUser tUser, HttpSession session) {
        TUser localUser = loginService.getLogin(tUser);
        if (localUser == null) {
            return "该用户不存在";
        }
        if (!localUser.getPassword().equals(tUser.getPassword())) {
            return "密码错误";
        }
        session.setAttribute("user", tUser);
        return "登录成功";
    }

}
