package com.rainbow.monitor.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.monitor.domain.DailyMonitor;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.service.DailyMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 日常监督信息管理
 */
@RestController
@RequestMapping("dailymonitor")
public class DailyMonitorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DailyMonitorService dailyMonitorService;

    /**
     * 添加日常监督信息
     *
     * @param
     * @return
     */
    @PostMapping("/addDailyMonitor")
    public ResponseBo add(@RequestBody DailyMonitor dailyMonitor) {
        int result = dailyMonitorService.addDailyMonitor(dailyMonitor);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改日常监督信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyDailyMonitor")
    public ResponseBo modify(@RequestBody DailyMonitor dailyMonitor) {

        int result = dailyMonitorService.modifyDailyMonitor(dailyMonitor);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取日常监督信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getDailyMonitorList")
    public ResponseBo getDailyMonitorList(@RequestBody Page page) {

        return dailyMonitorService.getDailyMonitorList(page);
    }

    /**
     * 获取日常监督信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geDailyMonitorById")
    public ResponseBo getDailyMonitorById(String id) {
        return dailyMonitorService.getDailyMonitorById(id);
    }

    /**
     * 删除日常监督信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteDailyMonitorByIds")
    public ResponseBo deleteDailyMonitorByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            dailyMonitorService.batchDelete(ids, "id", DailyMonitor.class);
        }
        return ResponseBo.ok();
    }
}
