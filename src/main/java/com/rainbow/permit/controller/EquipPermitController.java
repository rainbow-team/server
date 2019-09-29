package com.rainbow.permit.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.service.EquipPermitService;
import com.rainbow.permit.service.FacPermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 核安全设备许可信息管理
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
    @SystemLog(description = "添加核安全设备许可信息")
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
    @SystemLog(description = "修改核安全设备许可信息")
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
    @SystemLog(description = "删除核安全设备许可信息")
    public ResponseBo deleteEquipPermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            equipPermitService.batchDelete(ids, "id", EquipPermit.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 导出日常监督信息
     */
    @RequestMapping(value = "/exportEquipPermit", method = RequestMethod.GET)
    @SystemLog(description = "导出核安全设备许可信息")
    public void exportEquipPermit(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "equipDepartName", required = false) String equipDepartName,
            @RequestParam(value = "serviceDepartName", required = false) String serviceDepartName,
            @RequestParam(value = "facName", required = false) String facName,
            @RequestParam(value = "typeIds", required = false) String typeIds,
            @RequestParam(value = "levelIds", required = false) String levelIds,
            @RequestParam(value = "stageIds", required = false) String stageIds,
            @RequestParam(value = "start_date", required = false) String start_date,
            @RequestParam(value = "end_date", required = false) String end_date, HttpServletResponse response) {

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
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }

        Page page = new Page();
        page.setConditions(list);

        equipPermitService.exportEquipPermit(page, response);

    }

    /**
     * 导入
     * 
     * @param request
     * @return
     */
    @SystemLog(description = "导入核安全设备许可信息")
    @RequestMapping(value = "/importData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBo importData(HttpServletRequest request) {

        return equipPermitService.importData(request);
    }
}
