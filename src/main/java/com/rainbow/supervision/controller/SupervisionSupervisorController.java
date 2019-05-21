package com.rainbow.supervision.controller;

import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.attachment.service.FileInfoService;
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
    public ResponseBo getSupervisionSupervisorList(@RequestBody  SupervisionSupervisor supervisionSupervisor) {

        if(!StrUtil.isNullOrEmpty(supervisionSupervisor.getName())){
            String name = supervisionSupervisor.getName();
            Example example = new Example(SupervisionSupervisor.class);
            example.createCriteria().andLike("name",name);

            List<SupervisionSupervisor> list = supervisionSupervisorService.selectByExample(example);

            return ResponseBo.ok(list);
        }
        String name = supervisionSupervisor.getName();
        Example example = new Example(SupervisionSupervisor.class);
        example.createCriteria().andLike("name",name);


        List<SupervisionSupervisor> list = supervisionSupervisorService.selectAll();

        return ResponseBo.ok(list);
    }

    @PostMapping("saveOrUpdateSupervisionSupervisor")
    public ResponseBo saveOrUpdateSupervisionSupervisor(@RequestBody  SupervisionSupervisor supervisionSupervisor){
        return supervisionSupervisorService.saveOrUpdateSupervisionSupervisor(supervisionSupervisor);
    }

    @GetMapping("deleteSupervisionSupervisorById")
    public ResponseBo deleteSupervisionSupervisorById(String id){

        supervisionSupervisorService.deleteByKey(id);

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
