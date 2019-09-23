package com.rainbow.system.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.cache.EHCacheUtils;
import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.ResponseBo;

import com.rainbow.common.util.GuidHelper;
import com.rainbow.common.util.VeriyCode;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.system.service.UserService;

import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;


/**
 * @Author:deepblue
 * @Date:2019/4/29 18:09
 * @Description:
 **/
@CrossOrigin(allowCredentials="true")
@RestController
public class LoginController extends BaseController{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CODE_KEY = "_code";

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/login")
    public ResponseBo login(@RequestBody Map<String,String> map, HttpServletRequest  request) {

//        String code = request.getSession(true).getAttribute(CODE_KEY).toString();
//        String idyCode = map.get("code").toString();
//
//        if(!idyCode.equals(code)){
//            return ResponseBo.warn("验证码不正确!");
//        }


        Map<String, Object> result = new HashMap<>();

        SystemUser systemUser = userService.login(map);

        if (systemUser == null) {
            return ResponseBo.error("用户名或密码错误，请重试！");
        } else {
            List<String> permissions = userService.getAllPermissionByUserId(systemUser.getId());
            result.put("userinfo", systemUser);
            result.put("permission", permissions);

            String ticket = GuidHelper.getGuid();
            result.put("ticket", ticket);

            //去掉之前该用户的缓存(一个用户不能多次登录)
            EHCacheUtils.deleteCacheByUserId(cacheManager,systemUser.getId());
            EHCacheUtils.setCache(cacheManager,ticket,systemUser);

            return ResponseBo.ok(result);
        }
    }

    @RequestMapping("/loginout")
    @SystemLog(description="登出")
    public ResponseBo Loginout(){
        EHCacheUtils.deleteCache(cacheManager);
        return ResponseBo.ok();
    }

    /* 获取验证码图片*/
    @RequestMapping("/getVerifyCode")
    public void getVerificationCode(HttpServletResponse response, HttpServletRequest  request) {

        try {

            int width = 200;
            int height = 60;
            BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //生成对应宽高的初始图片
            String randomText = VeriyCode.drawRandomText(width, height, verifyImg);

            //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
//            request.getSession().setAttribute(CODE_KEY, randomText);
            Cookie cookie = new Cookie(CODE_KEY, randomText);
//            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);

            response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
            OutputStream os = response.getOutputStream(); //获取文件输出流

            ImageIO.write(verifyImg, "png", os);//输出图片流

            os.flush();
            os.close();//关闭流

        } catch (IOException e) {

        }
    }

    @GetMapping("/test")
    public String index(){
        return "test";
    }
}
