package com.rainbow.config.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.config.domain.SystemConfig;

import java.util.List;
import java.util.Map;

public interface SystemConfigMapper extends MyMapper<SystemConfig> {

    List<String> getAllTables();

    List<SystemConfig> getSystemConfigByTableName(String tableName);
}