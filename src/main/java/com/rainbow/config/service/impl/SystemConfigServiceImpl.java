package com.rainbow.config.service.impl;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.config.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author:deepblue
 * @Date:2019/5/15 10:17
 * @Description:
 **/

@Service("SystemConfigService")
public class SystemConfigServiceImpl extends BaseService<SystemConfig> implements SystemConfigService {

    @Autowired
    SystemConfigMapper systemConfigMapper;


    /**
     * 获取所有的配置，表名是key，值列表是List
     *
     * @return
     */
    @Override
    public Map<String, List<SystemConfig>> getAllSystemConfigList() {

        Map<String, List<SystemConfig>> map = new HashMap<String, List<SystemConfig>>();

        List<String> tables = systemConfigMapper.getAllTables();

        tables.forEach(table -> {
            if (table.toLowerCase().contains("config")) {
                List<SystemConfig> systemConfigs = systemConfigMapper.getSystemConfigByTableName(table.toString());
                map.put(table.toString().toLowerCase(), systemConfigs);
            }
        });
        return map;
    }

    /**
     * 根据表名和值，插入数据
     *
     * @param insertMap
     * @return
     */
    @Override
    public int saveConfigByTableNameAndValue(Map<String, String> insertMap) {

        insertMap.put("id", GuidHelper.getGuid());

        //int order = systemConfigMapper.getOrderByTableName(insertMap.get("tableName"));

        //insertMap.put("order", String.valueOf(order + 1));

        return systemConfigMapper.saveConfigByTableNameAndValues(insertMap);
    }

    /**
     * 根据表名，ID和值，修改数据
     *
     * @param map
     */
    @Override
    public void modifyConfig(Map<String, String> map) {
        systemConfigMapper.modifyConfigByTableNameAndValue(map);
    }

    @Override
    public ResponseBo getDicItemsByTable(String tableName) {

        Map<String, String> map = new HashMap<>();
        map.put("tableName", tableName);
        List<SystemConfig> list = systemConfigMapper.getDicItemsByTable(map);

        return ResponseBo.ok(list);
    }
}
