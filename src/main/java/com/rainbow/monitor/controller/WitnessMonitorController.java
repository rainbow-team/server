package com.rainbow.monitor.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 监督见证信息管理
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
    @SystemLog(description = "添加监督见证信息")
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
    @SystemLog(description = "修改监督见证信息")
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
    @SystemLog(description = "删除监督见证信息")
    public ResponseBo deleteWitnessMonitorByIds(@RequestBody String id) {
        if (id != null) {
            int result = witnessMonitorService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportWitnessMonitor", method = RequestMethod.GET)
    @SystemLog(description = "导出监督见证信息")
    public void exportWitnessMonitor(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "witness_obj", required = false) String witness_obj,
            @RequestParam(value = "witness_items", required = false) String witness_items,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!witness_obj.isEmpty()) {
            list.add(new Condition("witness_obj", witness_obj));
        }
        if (!witness_items.isEmpty()) {
            list.add(new Condition("witness_items", witness_items));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        witnessMonitorService.exportWitnessMonitor(page, response);

    }
    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @SystemLog(description = "导入监督见证信息")
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo importSupervisor(HttpServletRequest request) {

        return witnessMonitorService.importData(request);
    }
}
