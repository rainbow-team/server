package com.rainbow.check.controller;


import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.check.service.FacCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 13260 on 2019/5/11.
 * 核安全设备审评信息管理
 */
@RestController
@RequestMapping("equipcheck")
public class EquipCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EquipCheckService equipCheckService;

    /**
     * 添加核安全设备审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/addEquipCheck")
    public ResponseBo add(@RequestBody EquipCheck equipCheck) {
        int result = equipCheckService.addEquipCheck(equipCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全设备审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyEquipCheck")
    public ResponseBo modify(@RequestBody EquipCheck equipCheck) {

        int result = equipCheckService.modifyEquipCheck(equipCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核安全设备审评信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getEquipCheckList")
    public ResponseBo getEquipCheckList(@RequestBody Page page) {

        return equipCheckService.getEquipCheckList(page);
    }

    /**
     * 获取核安全设备审评信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geEquipCheckById")
    public ResponseBo getEquipCheckById(String id) {
        return equipCheckService.getEquipCheckById(id);
    }

    /**
     * 删除核安全设备审评信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteEquipCheckById")
    public ResponseBo deleteEquipCheckById(String id) {
        if (id != null) {
            int result = equipCheckService.deleteEquipCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }
}
