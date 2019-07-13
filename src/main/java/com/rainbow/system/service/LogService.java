package com.rainbow.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rainbow.common.service.IService;
import com.rainbow.system.domain.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Author:liuhao
 * @Date:2019/4/29 16:49
 * @Description:
 **/
public interface LogService extends IService<SysLog>{

    List<SysLog> findAllLogs(SysLog log);

    void deleteLogs(String logIds);

    @Async
    void saveLog(ProceedingJoinPoint point,SysLog log) throws JsonProcessingException;
}
