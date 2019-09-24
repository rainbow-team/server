package com.rainbow.security.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.WitnessMonitorService;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.service.FacSecurityService;
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
 * Created by 13260 on 2019/5/11. 核设施安全问题管理
 */
@RestController
@RequestMapping("facsecurity")
public class FacSecurityController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacSecurityService facSecurityService;

    /**
     * 添加核设施安全问题
     *
     * @param
     * @return
     */
    @PostMapping("/addFacSecurity")
    @SystemLog(description = "添加核设施安全问题")
    public ResponseBo add(@RequestBody FacSecurity facSecurity) {
        int result = facSecurityService.addFacSecurity(facSecurity);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施安全问题
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacSecurity")
    @SystemLog(description = "修改核设施安全问题")
    public ResponseBo modify(@RequestBody FacSecurity facSecurity) {

        int result = facSecurityService.modifyFacSecurity(facSecurity);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取核设施安全问题
     *
     * @param page
     * @return
     */
    @PostMapping("/getFacSecurityList")
    public ResponseBo getFacSecurityList(@RequestBody Page page) {

        return facSecurityService.getFacSecurityList(page);
    }

    /**
     * 获取核设施安全问题详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geFacSecurityById")
    public ResponseBo getFacSecurityById(String id) {
        return facSecurityService.getFacSecurityById(id);
    }

    /**
     * 删除核设施安全问题信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteFacSecurityById")
    @SystemLog(description = "删除核设施安全问题信息")
    public ResponseBo deleteFacSecurityById(@RequestBody String id) {
        if (id != null) {
            int result = facSecurityService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出核设施安全问题
     */
    @RequestMapping(value = "/exportFacSecurity", method = RequestMethod.GET)
    @SystemLog(description = "导出核设施安全问题")
    public void exportFacSecurity(@RequestParam(value = "serviceDepartName", required = false) String serviceDepartName,
            @RequestParam(value = "facName", required = false) String facName,
            @RequestParam(value = "facStatusTypeIds", required = false) String facStatusTypeIds,
            @RequestParam(value = "checkTypeIds", required = false) String checkTypeIds,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date,
            @RequestParam(value = "questionTypeIds", required = false) String questionTypeIds,
            @RequestParam(value = "questionNatureIds", required = false) String questionNatureIds,
            @RequestParam(value = "reformStatusTypeIds", required = false) String reformStatusTypeIds,
            HttpServletResponse response) {

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
        if (!checkTypeIds.isEmpty()) {
            list.add(new Condition("checkLevelIds", Stream.of(checkTypeIds).collect(toList())));
        }
        if (!content.isEmpty()) {
            list.add(new Condition("content", content));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }
        if (!questionTypeIds.isEmpty()) {
            list.add(new Condition("questionTypeIds", Stream.of(questionTypeIds).collect(toList())));
        }
        if (!questionNatureIds.isEmpty()) {
            list.add(new Condition("questionNatureIds", Stream.of(questionNatureIds).collect(toList())));
        }
        if (!reformStatusTypeIds.isEmpty()) {
            list.add(new Condition("reformStatusTypeIds", Stream.of(reformStatusTypeIds).collect(toList())));
        }

        Page page = new Page();
        page.setConditions(list);

        facSecurityService.exportFacSecurity(page, response);

    }

    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @SystemLog(description = "导入核设施安全问题")
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo importData(HttpServletRequest request) {

        return facSecurityService.importData(request);
    }
}
