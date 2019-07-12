package com.rainbow.security.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.WitnessMonitorService;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.service.FacSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设施安全问题管理
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
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacSecurityByIds")
    public ResponseBo deleteFacSecurityByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            facSecurityService.batchDelete(ids, "id", FacSecurity.class);
        }
        return ResponseBo.ok();
    }
}
