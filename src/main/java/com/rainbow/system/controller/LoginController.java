package com.rainbow.system.controller;

import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.MD5Utils;
import com.rainbow.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:deepblue
 * @Date:2019/4/29 18:09
 * @Description:
 **/
@Controller
public class LoginController extends BaseController{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CODE_KEY = "_code";

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }

    @PostMapping("/login1")
    @ResponseBody
    public ResponseBo login(String username, String password, String code, Boolean rememberMe){
       /* if (!StringUtils.isNotBlank(code)) {
            return ResponseBo.warn("验证码不能为空！");
        }

        Session session = super.getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);

        if (!code.equalsIgnoreCase(sessionCode)) {
            return ResponseBo.warn("验证码错误！");
        }*/
//        password = MD5Utils.encrypt(username.toLowerCase(), password);
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
          /*  Subject subject = getSubject();
            if (subject != null) {
                subject.logout();
            }
            super.login(token);*/
//            this.userService.updateLoginTime(username);
            return ResponseBo.ok(111);
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }
}
