package com.rainbow.monitor.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.monitor.domain.CheckFileMonitor;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.CheckMonitorService;
import com.rainbow.monitor.service.ReportMonitorService;
import com.rainbow.monitor.service.WitnessMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 监督见证信息管理
 */
@RestController
@RequestMapping("witnessmonitor")
public class WitnessMonitorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WitnessMonitorService witnessMonitorService;

    /**
     * 添加监督见证信息
     *
     * @param
     * @return
     */
    @PostMapping("/addWitnessMonitor")
    @SystemLog(description="添加监督见证信息")
    public ResponseBo add(@RequestBody WitnessMonitor witnessMonitor) {
        int result = witnessMonitorService.addWitnessMonitor(witnessMonitor);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改监督见证信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyWitnessMonitor")
    @SystemLog(description="修改监督见证信息")
    public ResponseBo modify(@RequestBody WitnessMonitor witnessMonitor) {

        int result = witnessMonitorService.modifyWitnessMonitor(witnessMonitor);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取监督见证信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getWitnessMonitorList")
    public ResponseBo getWitnessMonitorList(@RequestBody Page page) {

        return witnessMonitorService.getWitnessMonitorList(page);
    }

    /**
     * 获取监督见证信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geWitnessMonitorById")
    public ResponseBo getWitnessMonitorById(String id) {
        return witnessMonitorService.getWitnessMonitorById(id);
    }

    /**
     * 删除监督见证信息信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteWitnessMonitorById")
    @SystemLog(description="删除监督见证信息")
    public ResponseBo deleteWitnessMonitorByIds(@RequestBody String id) {
        if (id != null) {
            int result = witnessMonitorService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }
}
