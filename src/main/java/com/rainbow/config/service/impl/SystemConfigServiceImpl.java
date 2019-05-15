package com.rainbow.config.service.impl;

import com.rainbow.common.service.impl.BaseService;
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

    @Override
    public Map<String, List<SystemConfig>> getAllSystemConfigList() {

        Map<String, List<SystemConfig>> map = new HashMap<String, List<SystemConfig>>();

        List<String> tables = systemConfigMapper.getAllTables();

        tables.forEach(table -> {
            if (table.contains("config")) {
                List<SystemConfig> systemConfigs = systemConfigMapper.getSystemConfigByTableName(table.toString());
                map.put(table.toString(), systemConfigs);
            }
        });
        return map;
    }
}
