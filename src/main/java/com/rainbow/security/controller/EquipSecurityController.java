package com.rainbow.security.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.security.domain.EquipSecurity;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.service.EquipSecurityService;
import com.rainbow.security.service.FacSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核安全设备安全问题管理
 */
@RestController
@RequestMapping("equipsecurity")
public class EquipSecurityController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EquipSecurityService equipSecurityService;

    /**
     * 添加核安全设备安全问题
     *
     * @param
     * @return
     */
    @PostMapping("/addEquipSecurity")
    public ResponseBo add(@RequestBody EquipSecurity equipSecurity) {
        int result = equipSecurityService.addEquipSecurity(equipSecurity);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全设备安全问题
     *
     * @param
     * @return
     */
    @PostMapping("/modifyEquipSecurity")
    public ResponseBo modify(@RequestBody EquipSecurity equipSecurity) {

        int result = equipSecurityService.modifyEquipSecurity(equipSecurity);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核安全设备安全问题
     *
     * @param page
     * @return
     */
    @PostMapping("/getEquipSecurityList")
    public ResponseBo getEquipSecurityList(@RequestBody Page page) {

        return equipSecurityService.getEquipSecurityList(page);
    }

    /**
     * 获取核安全设备安全问题详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geEquipSecurityById")
    public ResponseBo getEquipSecurityById(String id) {
        return equipSecurityService.getEquipSecurityById(id);
    }

    /**
     * 删除核安全设备安全问题信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteEquipSecurityByIds")
    public ResponseBo deleteEquipSecurityByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            equipSecurityService.batchDelete(ids, "id", EquipSecurity.class);
        }
        return ResponseBo.ok();
    }
}
