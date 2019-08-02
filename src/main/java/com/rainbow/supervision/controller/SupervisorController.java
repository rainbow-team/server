package com.rainbow.supervision.controller;

import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.Supervisor;
import com.rainbow.supervision.service.SupervisorService;
import com.rainbow.supervision.service.SupervisorTrainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        if (id != null) {
            int result = supervisorService.deleteSupervisorById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();

        //supervisionTrainRecordService.batchDelete(ids,"supervisorId", SupervisorTrainRecord.class);
        //supervisorService.batchDelete(ids,"id",Supervisor.class);
        //
        //return  ResponseBo.ok();
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


        //Supervisor supervisor = supervisorService.selectByKey(id);
        //
        //Example example = new Example(FileInfo.class);
        //example.createCriteria().andEqualTo("fileinfoRefId",id);
        //List<FileInfo> list =fileInfoService.selectByExample(example);
        //supervisor.setAttachmentList(list);
        //
        //return  ResponseBo.ok(supervisor);
    }
}
