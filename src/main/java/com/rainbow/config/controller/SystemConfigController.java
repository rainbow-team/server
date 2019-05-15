package com.rainbow.config.controller;

import com.rainbow.common.controller.BaseController;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.config.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/15 11:44
 * @Description:
 **/

@Controller
@RequestMapping("/config")
public class SystemConfigController extends BaseController{

    @Autowired
    SystemConfigService systemConfigService;

    @RequestMapping("/getAllSystemConfig")
    public Map<String,List<SystemConfig>> getAllSystemConfig() {
        Map<String, List<SystemConfig>> map;
        map = systemConfigService.getAllSystemConfigList();

        return map;
    }
}
