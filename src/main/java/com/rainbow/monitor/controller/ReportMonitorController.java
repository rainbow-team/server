package com.rainbow.monitor.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.service.CheckMonitorService;
import com.rainbow.monitor.service.ReportMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 监督报告信息管理
 */
@RestController
@RequestMapping("reportmonitor")
public class ReportMonitorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReportMonitorService reportMonitorService;

    /**
     * 添加监督报告信息
     *
     * @param
     * @return
     */
    @PostMapping("/addReportMonitor")
    @SystemLog(description = "添加监督报告信息")
    public ResponseBo add(@RequestBody ReportMonitor reportMonitor) {
        int result = reportMonitorService.addReportMonitor(reportMonitor);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改监督报告信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyReportMonitor")
    @SystemLog(description = "修改监督报告信息")
    public ResponseBo modify(@RequestBody ReportMonitor reportMonitor) {

        int result = reportMonitorService.modifyReportMonitor(reportMonitor);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取监督报告信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getReportMonitorList")
    public ResponseBo getReportMonitorList(@RequestBody Page page) {

        return reportMonitorService.getReportMonitorList(page);
    }

    /**
     * 获取监督报告信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geReportMonitorById")
    public ResponseBo getReportMonitorById(String id) {
        return reportMonitorService.getReportMonitorById(id);
    }

    /**
     * 删除监督报告信息信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteReportMonitorById")
    @SystemLog(description = "删除监督报告信息")
    public ResponseBo deleteReportMonitorByIds(@RequestBody String id) {

        if (id != null) {
            int result = reportMonitorService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportReportMonitor", method = RequestMethod.GET)
    @SystemLog(description = "导出监督报告信息")
    public void exportDailyMonitor(
            @RequestParam(value = "orgName", required = false) String orgName,
            @RequestParam(value = "typeIds", required = false) String typeIds,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!orgName.isEmpty()) {
            list.add(new Condition("orgName", orgName));
        }
        if (!typeIds.isEmpty()) {
            list.add(new Condition("typeIds", Stream.of(typeIds).collect(toList())));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        reportMonitorService.exportReportMonitor(page, response);

    }

    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = "导入监督报告信息")
    public ResponseBo importSupervisor(HttpServletRequest request) {

        return reportMonitorService.importData(request);
    }
}
