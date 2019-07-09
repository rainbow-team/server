package com.rainbow.permit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.service.EquipPermitService;
import com.rainbow.permit.service.FacPermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核安全设备许可信息管理
 */
@RestController
@RequestMapping("equippermit")
public class EquipPermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EquipPermitService equipPermitService;

    /**
     * 添加核安全设备许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addEquipPermit")
    public ResponseBo add(@RequestBody EquipPermit equipPermit) {
        int result = equipPermitService.addEquipPermit(equipPermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全设备许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyEquipPermit")
    public ResponseBo modify(@RequestBody EquipPermit equipPermit) {

        int result = equipPermitService.modifyEquipPermit(equipPermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核安全设备许可信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getEquipPermitList")
    public ResponseBo getEquipPermitList(@RequestBody Page page) {

        return equipPermitService.getEquipPermitList(page);
    }

    /**
     * 获取核安全设备许可信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getEquipPermitById")
    public ResponseBo getEquipPermitById(String id) {
        return equipPermitService.getEquipPermitById(id);
    }

    /**
     * 删除核安全设备许可信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteEquipPermitByIds")
    public ResponseBo deleteEquipPermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            equipPermitService.batchDelete(ids, "id", EquipPermit.class);
        }
        return ResponseBo.ok();
    }
}
