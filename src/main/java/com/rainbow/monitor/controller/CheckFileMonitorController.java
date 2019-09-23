package com.rainbow.monitor.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.monitor.domain.CheckFileMonitor;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.CheckFileMonitorService;
import com.rainbow.monitor.service.CheckMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 监督检查文件信息管理
 */
@RestController
@RequestMapping("checkfilemonitor")
public class CheckFileMonitorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CheckFileMonitorService checkFileMonitorService;

    /**
     * 添加监督检查文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/addCheckFileMonitor")
    @SystemLog(description="添加监督检查文件信息")
    public ResponseBo add(@RequestBody CheckFileMonitor checkMonitor) {
        int result = checkFileMonitorService.addCheckFileMonitor(checkMonitor);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改监督检查文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyCheckFileMonitor")
    @SystemLog(description="修改监督检查文件信息")
    public ResponseBo modify(@RequestBody CheckFileMonitor checkMonitor) {

        int result = checkFileMonitorService.updateAll(checkMonitor);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取监督检查文件信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getCheckFileMonitorList")
    public ResponseBo getCheckFileMonitorList(@RequestBody Page page) {

        return checkFileMonitorService.getCheckFileMonitorList(page);
    }

    /**
     * 获取监督检查文件信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geCheckFileMonitorById")
    public ResponseBo getCheckFileMonitorById(String id) {
        return checkFileMonitorService.getCheckFileMonitorById(id);
    }

    /**
     * 删除监督检查文件信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteCheckFileMonitorByIds")
    @SystemLog(description="删除监督检查文件信息")
    public ResponseBo deleteCheckMonitorByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            checkFileMonitorService.batchDelete(ids, "id", CheckFileMonitor.class);
        }
        return ResponseBo.ok();
    }
}
