package com.rainbow.monitor.controller;


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
    public ResponseBo add(@RequestBody WitnessMonitor activityCheck) {
        int result = witnessMonitorService.addWitnessMonitor(activityCheck);

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
    public ResponseBo modify(@RequestBody WitnessMonitor activityCheck) {

        int result = witnessMonitorService.modifyWitnessMonitor(activityCheck);
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
     * @param ids
     * @return
     */
    @PostMapping("/deleteWitnessMonitorByIds")
    public ResponseBo deleteWitnessMonitorByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            witnessMonitorService.batchDelete(ids, "id", WitnessMonitor.class);
        }
        return ResponseBo.ok();
    }
}
