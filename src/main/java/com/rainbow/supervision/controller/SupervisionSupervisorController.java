package com.rainbow.supervision.controller;

import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.StrUtil;
import com.rainbow.supervision.domain.SupervisionSupervisor;
import com.rainbow.supervision.service.SupervisionSupervisorService;
import com.rainbow.system.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

/**
 * Created by 13260 on 2019/5/11.
 */
@RestController
@RequestMapping("SupervisionSupervisor")
public class SupervisionSupervisorController {

    @Autowired
    public SupervisionSupervisorService supervisionSupervisorService;

    @Autowired
    public FileInfoService fileInfoService;

    @PostMapping("getSupervisionSupervisorList")
    public ResponseBo getSupervisionSupervisorList(@RequestBody Page page) {

        return supervisionSupervisorService.getSupervisionSupervisorList(page);
    }

    @PostMapping("saveOrUpdateSupervisionSupervisor")
    public ResponseBo saveOrUpdateSupervisionSupervisor(@RequestBody  SupervisionSupervisor supervisionSupervisor){
        return supervisionSupervisorService.saveOrUpdateSupervisionSupervisor(supervisionSupervisor);
    }

    @PostMapping("deleteSupervisionSupervisorByIds")
    public ResponseBo deleteSupervisionSupervisorById(@RequestBody List<String> ids){

        supervisionSupervisorService.batchDelete(ids,"id",SupervisionSupervisor.class);

        return  ResponseBo.ok();
    }

    @GetMapping("getSupervisionSupervisorById")
    public ResponseBo getSupervisionSupervisorById(String id){

        SupervisionSupervisor supervisionSupervisor=supervisionSupervisorService.selectByKey(id);

        Example example = new Example(FileInfo.class);
        example.createCriteria().andEqualTo("fileinfoRefId",id);
        List<FileInfo> list =fileInfoService.selectByExample(example);
        supervisionSupervisor.setAttachmentList(list);

        return  ResponseBo.ok(supervisionSupervisor);
    }
}
