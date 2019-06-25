package com.rainbow.config.controller;

import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.GuidHelper;
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

@RestController
@RequestMapping("/config")
public class SystemConfigController extends BaseController {

    @Autowired
    SystemConfigService systemConfigService;

    @RequestMapping("/getAllConfig")
    public Map<String, List<SystemConfig>> getAllSystemConfig() {
        return systemConfigService.getAllSystemConfigList();
    }

    /**
     *
     * @param map map要存需要新增的表名字，value分别作为主键，他们的值作为Value
     * @return
     */
    @RequestMapping("/addConfig")
    public ResponseBo addConfig(Map<String, String> map) {
        if (map != null) {
            systemConfigService.saveConfigByTableNameAndValue(map);
        }
        return ResponseBo.ok("保存成功");
    }

    @RequestMapping("/modifyConfig")
    public ResponseBo modifyConfig(Map<String,String> map) {
        if (map != null) {
            systemConfigService.modifyConfig(map);
        }
        return ResponseBo.ok("修改成功");
    }
}
