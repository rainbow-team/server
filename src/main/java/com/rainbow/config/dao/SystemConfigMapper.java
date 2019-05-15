package com.rainbow.config.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.config.domain.SystemConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemConfigMapper extends MyMapper<SystemConfig> {

    List<String> getAllTables();

    List<SystemConfig> getSystemConfigByTableName(String tableName);

    int saveConfigByTableNameAndValues(Map<String,String> insertMap);

    int deleteConfigByTableNameAndKey(String tableName,String key);

    /**
     *
     * @param tableName 查找表中order 最大的值
     * @return
     */
    int getOrderByTableName(String tableName);

    /**
     * 根据表名和值更新config中的值
     * @param map 包含表名和待更新的值作为主键
     * @return
     */
    void modifyConfigByTableNameAndValue(@Param("map") Map<String,String> map);
}