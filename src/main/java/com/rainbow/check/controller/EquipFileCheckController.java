package com.rainbow.check.controller;


import com.rainbow.check.domain.EquipFileCheck;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.check.service.EquipFileCheckService;
import com.rainbow.check.service.FacFileCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核安全设备审评文件信息管理
 */
@RestController
@RequestMapping("eqiupfilecheck")
public class EquipFileCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EquipFileCheckService equipFileCheckService;

    /**
     * 添加核设施审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/addEquipFileCheck")
    public ResponseBo add(@RequestBody EquipFileCheck equipFileCheck) {
        int result = equipFileCheckService.addEquipFileCheck(equipFileCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施审评文件信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyEquipFileCheck")
    public ResponseBo modify(@RequestBody EquipFileCheck equipFileCheck) {

        int result = equipFileCheckService.updateAll(equipFileCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取核设施审评文件信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getEquipFileCheckList")
    public ResponseBo getFacFileCheckList(@RequestBody Page page) {

        return equipFileCheckService.getEquipFileCheckList(page);
    }

    /**
     * 获取核设施审评文件详情
     *
     * @param id
     * @return
     */
    @PostMapping("/getEquipFileCheckById")
    public ResponseBo getFacFileCheckById(String id) {
        return equipFileCheckService.getEquipFileCheckById(id);
    }

    /**
     * 删除核设施审评文件信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteEquipFileCheckByIds")
    public ResponseBo deleteFacFileCheckByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            equipFileCheckService.batchDelete(ids, "id", EquipFileCheck.class);
        }
        return ResponseBo.ok();
    }
}
