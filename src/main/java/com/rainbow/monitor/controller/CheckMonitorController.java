package com.rainbow.monitor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.service.CheckMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 监督检查信息管理
 */
@RestController
@RequestMapping("checkmonitor")
public class CheckMonitorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CheckMonitorService checkMonitorService;

    /**
     * 添加监督检查信息
     *
     * @param
     * @return
     */
    @PostMapping("/addCheckMonitor")
    @SystemLog(description = "添加监督检查信息")
    public ResponseBo add(@RequestBody CheckMonitor checkMonitor) {
        int result = checkMonitorService.addCheckMonitor(checkMonitor);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改监督检查信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyCheckMonitor")
    @SystemLog(description = "修改监督检查信息")
    public ResponseBo modify(@RequestBody CheckMonitor checkMonitor) {

        int result = checkMonitorService.modifyCheckMonitor(checkMonitor);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取监督检查信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getCheckMonitorList")
    public ResponseBo getCheckMonitorList(@RequestBody Page page) {

        return checkMonitorService.getCheckMonitorList(page);
    }

    /**
     * 获取监督检查信息详情
     *
     * @param id
     * @return
     */
    @PostMapping("/geCheckMonitorById")
    public ResponseBo getCheckMonitorById(@RequestBody String id) {
        return checkMonitorService.getCheckMonitorById(id);
    }

    /**
     * 删除监督检查信息信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteCheckMonitorById")
    @SystemLog(description = "删除监督检查信息")
    public ResponseBo deleteCheckMonitorById(@RequestBody String id) {
        if (id != null) {
            int result = checkMonitorService.deleteCheckMonitorById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportCheckMonitor", method = RequestMethod.GET)
    @SystemLog(description = "导出监督检查信息")
    public void exportCheckMonitor(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "typeIds", required = false) String typeIds,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!content.isEmpty()) {
            list.add(new Condition("facName", content));
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

        checkMonitorService.exportCheckMonitor(page, response);

    }

    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @SystemLog(description = "导入监督检查信息")
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo importSupervisor(HttpServletRequest request) {

        return checkMonitorService.importData(request);
    }
}
