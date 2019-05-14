package com.rainbow.common.aspect;

import com.rainbow.common.config.RainbowProperties;
import com.rainbow.common.util.HttpContextUtils;
import com.rainbow.common.util.IPUtils;
import com.rainbow.system.domain.SysLog;
import com.rainbow.system.domain.User;
import com.rainbow.system.service.LogService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:liuhao
 * @Date:2019/4/28 18:01
 * @Description:AOP记录用户操作日志
 **/

@Aspect
@Component
public class LogAspect {

    @Autowired
    private RainbowProperties rainbowProperties;

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.rainbow.common.annotation.Log)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        //执行方法
        result = point.proceed();

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        //设置IP地址
        String ip = IPUtils.getIpAddr(request);

        long time = System.currentTimeMillis() - beginTime;
        if (rainbowProperties.isOpenAOPLog()) {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            SysLog log = new SysLog();
            log.setUsername(user.getUsername());
            log.setIp(ip);
            log.setTime(time);
            logService.saveLog(point, log);
        }

        return result;
    }
}
