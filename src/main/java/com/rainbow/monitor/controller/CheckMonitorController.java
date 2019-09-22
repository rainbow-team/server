package com.rainbow.monitor.controller;

import javax.servlet.http.HttpServletRequest;

import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.service.CheckMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseBo deleteCheckMonitorById(@RequestBody String id) {
        if (id != null) {
            int result = checkMonitorService.deleteCheckMonitorById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo importSupervisor(HttpServletRequest request) {

        return checkMonitorService.importData(request);
    }
}
