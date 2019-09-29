package com.rainbow.check.controller;


import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.check.service.FacCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
    @SystemLog(description="添加核安全设备审评信息")
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
    @SystemLog(description="修改核安全设备审评信息")
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
    @PostMapping("/geEquipCheckById")
    public ResponseBo getEquipCheckById(@RequestBody String id) {
        return equipCheckService.getEquipCheckById(id);
    }

    /**
     * 删除核安全设备审评信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteEquipCheckById")
    @SystemLog(description="删除核安全设备审评信息")
    public ResponseBo deleteEquipCheckById(@RequestBody String id) {
        if (id != null) {
            int result = equipCheckService.deleteEquipCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出核安全设备审评信息
     */
    @RequestMapping(value = "/exportEquipCheck", method = RequestMethod.GET)
    @SystemLog(description = "导出核安全设备审评信息")
    public void exportEquipCheck(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "equipDepartName", required = false) String equipDepartName,
            @RequestParam(value = "serviceDepartName", required = false) String serviceDepartName,
            @RequestParam(value = "facName", required = false) String facName,
            @RequestParam(value = "typeIds", required = false) String typeIds,
            @RequestParam(value = "levelIds", required = false) String levelIds,
            @RequestParam(value = "stageIds", required = false) String stageIds,
            HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!equipDepartName.isEmpty()) {
            list.add(new Condition("equipDepartName", equipDepartName));
        }
        if (!serviceDepartName.isEmpty()) {
            list.add(new Condition("serviceDepartName", serviceDepartName));
        }
        if (!facName.isEmpty()) {
            list.add(new Condition("facName", facName));
        }
        if (!typeIds.isEmpty()) {
            list.add(new Condition("typeIds", Stream.of(typeIds).collect(toList())));
        }
        if (!levelIds.isEmpty()) {
            list.add(new Condition("levelIds", Stream.of(levelIds).collect(toList())));
        }
        if (!stageIds.isEmpty()) {
            list.add(new Condition("stageIds", Stream.of(stageIds).collect(toList())));
        }

        Page page = new Page();
        page.setConditions(list);

        equipCheckService.exportEquipCheck(page, response);

    }
}
