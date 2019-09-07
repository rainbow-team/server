package com.rainbow.supervision.controller;

import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.Supervisor;
import com.rainbow.supervision.service.SupervisorService;
import com.rainbow.supervision.service.SupervisorTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 */
@RestController
@RequestMapping("Supervisor")
public class SupervisorController {

    @Autowired
    public SupervisorService supervisorService;

    @Autowired
    public FileInfoService fileInfoService;

    @Autowired
    SupervisorTrainRecordService supervisionTrainRecordService;


    /**
     * 新增核安全监督员
     * @param supervisor
     * @return
     */
    @PostMapping("/addSupervisor")
    public ResponseBo addSupervisor(@RequestBody Supervisor supervisor){
        int result = supervisorService.addSupervisor(supervisor);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全监督员
     * @param supervisor
     * @return
     */
    @PostMapping("/modifySupervisor")
    public ResponseBo modifySupervisor(@RequestBody Supervisor supervisor){
        int result = supervisorService.modifySupervisor(supervisor);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 根据核安全监督员主键集合删除
     * @param id
     * @return
     */
    @PostMapping("/deleteSupervisorById")
    public ResponseBo deleteSupervisionSupervisorById(@RequestBody String id){

        return supervisorService.deleteSupervisorById(id);
    }

    /**
     * 获取所有的监督员记录
     * @param page
     * @return
     */
    @PostMapping("/getSupervisorList")
    public ResponseBo getSupervisionSupervisorList(@RequestBody Page page) {

        return supervisorService.getSupervisorList(page);
    }


    /**
     *根据核安全监督员主键获取监督员的详细信息
     * @param id
     * @return
     */
    @PostMapping("/getSupervisorById")
    public ResponseBo getSupervisorById(@RequestBody String id) {

        if (id != null) {
            return supervisorService.getSupervisorById(id);
        }
        return ResponseBo.ok("获取失败");

    }

    /**
     * 导出核安全监督员
     * @param name
     * @param orgName
     * @param start_date
     * @param end_date
     * @param typeIds
     * @param response
     */
    @RequestMapping(value = "/exportSupervisor", method = RequestMethod.GET)
    public void exportSupervisor( @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "orgName", required = false) String orgName,
                                  @RequestParam(value = "start_date", required = false) String start_date,
                                  @RequestParam(value = "end_date", required = false) String end_date,
                                  @RequestParam(value = "typeIds", required = false) String typeIds,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!orgName.isEmpty()) {
            list.add(new Condition("orgName", orgName));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }
        if (!typeIds.isEmpty()) {
            List<String> listTypeIds = new ArrayList<>();
            listTypeIds.add(typeIds);
            list.add(new Condition("typeIds", listTypeIds));
        }

        Page page = new Page();
        page.setConditions(list);

        supervisorService.exportSupervisor(page,response);

    }
}
