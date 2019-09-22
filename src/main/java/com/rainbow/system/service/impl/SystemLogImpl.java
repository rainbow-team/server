package com.rainbow.system.service.impl;

import com.rainbow.common.cache.EHCacheUtils;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.common.util.StrUtil;
import com.rainbow.config.dao.SystemLogMapper;
import com.rainbow.config.domain.SystemLog;
import com.rainbow.system.domain.SystemUser;
import net.sf.ehcache.CacheManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Created by 13260 on 2019/9/18.
 */
@Aspect
@Component
public class SystemLogImpl {

    @Autowired
    SystemLogMapper systemLogMapper;

    @Autowired
    CacheManager cacheManager;

    //保存日志
    @Pointcut("execution(* com.rainbow.*.controller.*.*(..))")
    public void  SaveLog(){
    }

    //保存登陆日志
    @Pointcut("execution(* com.rainbow.system.controller.LoginController.login(..))")
    public void  SaveLoginLog(){
    }

    @After( "SaveLog()")
    public void  SaveFgLog(JoinPoint point){

        String comment = getServiceMthodDescription(point);
        if(StrUtil.isNullOrEmpty(comment)){
            return;
        }

        Object[] args=point.getArgs();
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);

        if(user!=null){
            //日志对象
            SystemLog log = new SystemLog();
            log.setId(GuidHelper.getGuid());
            log.setOperContent(comment);
            log.setUserId(user.getId());
            log.setUserName(user.getUsername());
            log.setOperTime(new Date());

            systemLogMapper.insert(log);
        }

    }

    @AfterReturning(pointcut = "SaveLoginLog()", returning = "returnValue")
    public void SaveLoginLog(JoinPoint point,Object returnValue){
        ResponseBo responseBo = (ResponseBo) returnValue;

        if(responseBo!=null){
            if(responseBo.get("msg")!=null&&responseBo.get("code")=="200"){

                Map<String,Object> mapUser = (Map<String,Object>)responseBo.get("msg");
                SystemUser user = (SystemUser) mapUser.get("userinfo");
                //日志对象
                SystemLog log = new SystemLog();
                log.setId(GuidHelper.getGuid());
                log.setOperContent("登录");
                log.setUserId(user.getId());
                log.setUserName(user.getUsername());
                log.setOperTime(new Date());

                systemLogMapper.insert(log);
            }
        }
    }

    /**
     * 查询方法描述
     * @param joinPoint
     * @return
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass;
        String description = "";
        try {
            targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();

            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        if(method.getAnnotation(com.rainbow.common.annotation.SystemLog.class)!=null){
                            description = method.getAnnotation(com.rainbow.common.annotation.SystemLog.class).description();
                        }
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        return description;
    }
}
