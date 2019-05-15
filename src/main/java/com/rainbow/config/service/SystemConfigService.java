package com.rainbow.config.service;

import com.rainbow.common.service.IService;
import com.rainbow.config.domain.SystemConfig;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/15 10:15
 * @Description:
 **/
public interface SystemConfigService extends IService<SystemConfig> {

    Map<String,List<SystemConfig>>  getAllSystemConfigList();


}
