package com.rainbow.monitor.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.monitor.domain.DailyMonitor;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.service.DailyMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 日常监督信息管理
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
    @SystemLog(description = "添加日常监督信息")
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
    @SystemLog(description = "修改日常监督信息")
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
     * @param id
     * @return
     */
    @PostMapping("/deleteDailyMonitorById")
    @SystemLog(description = "删除日常监督信息")
    public ResponseBo deleteDailyMonitorById(@RequestBody String id) {
        if (id != null) {
            int result = dailyMonitorService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportDailyMonitor", method = RequestMethod.GET)
    @SystemLog(description = "导出日常监督信息")
    public void exportDailyMonitor(
            @RequestParam(value = "serviceDepartName", required = false) String serviceDepartName,
            @RequestParam(value = "facName", required = false) String facName,
            @RequestParam(value = "facStatusTypeIds", required = false) String facStatusTypeIds,
            @RequestParam(value = "fileTypeIds", required = false) String fileTypeIds,
            @RequestParam(value = "file_name", required = false) String file_name,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!serviceDepartName.isEmpty()) {
            list.add(new Condition("serviceDepartName", serviceDepartName));
        }
        if (!facName.isEmpty()) {
            list.add(new Condition("facName", facName));
        }
        if (!facStatusTypeIds.isEmpty()) {
            list.add(new Condition("facStatusTypeIds", Stream.of(facStatusTypeIds).collect(toList())));
        }
        if (!fileTypeIds.isEmpty()) {
            list.add(new Condition("fileTypeIds", Stream.of(fileTypeIds).collect(toList())));
        }
        if (!file_name.isEmpty()) {
            list.add(new Condition("file_name", file_name));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        dailyMonitorService.exportDailyMonitor(page, response);

    }

    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = "导入日常监督信息")
    public ResponseBo importSupervisor(HttpServletRequest request) {

        return dailyMonitorService.importData(request);
    }
}
