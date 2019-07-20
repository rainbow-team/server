package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设备单位管理
 */
@RestController
@RequestMapping("equipdepart")
public class EquipDepartController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EquipDepartService equipDepartService;

    /**
     * 添加核设备单位信息
     *
     * @param
     * @return
     */
    @PostMapping("/addEquipDepart")
    public ResponseBo add(@RequestBody EquipDepart equipDepart) {
        int result = equipDepartService.addEquipDepart(equipDepart);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设备单位信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyEquipDepart")
    public ResponseBo modify(@RequestBody EquipDepart equipDepart) {

        int result = equipDepartService.modifyEquipDepart(equipDepart);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核设备单位信息列表
     * @param page
     * @return
     */
    @PostMapping("/getEquipDepartList")
    public ResponseBo getEquipDepartList(@RequestBody Page page){

        return equipDepartService.getEquipDepartList(page);
    }

    /**
     * 获取核设备单位信息详情
     * @param id
     * @return
     */
    @GetMapping("/getEquipDepartById")
    public ResponseBo getEquipDepartById(String id){
        EquipDepart result =  equipDepartService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除核设备单位信息
     * @param id
     * @return
     */
    @PostMapping("/deleteEquipDepartById")
    public ResponseBo deleteGroupById(@RequestBody String id){
        if (id != null) {
            int result = equipDepartService.deleteEquipDepartById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 获取设备单位信息
     *
     * @param
     * @return
     */
    @PostMapping("/getAllEquipDepart")
    public ResponseBo getAllEquipDepart() {

        List<EquipDepart> result = equipDepartService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
