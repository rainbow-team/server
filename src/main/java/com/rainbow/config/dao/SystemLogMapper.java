package com.rainbow.config.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.config.domain.SystemLog;

import java.util.List;
import java.util.Map;

public interface SystemLogMapper extends MyMapper<SystemLog> {

    List<SystemLog> getSystemLogList(Map<String, Object> map);

    int deleteSystemLog();
}