package com.rainbow.security.controller;


import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.security.domain.EquipSecurity;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.service.EquipSecurityService;
import com.rainbow.security.service.FacSecurityService;
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
     * @param id
     * @return
     */
    @PostMapping("/deleteEquipSecurityById")
    public ResponseBo deleteEquipSecurityById(@RequestBody String id) {
        if (id != null) {
            int result = equipSecurityService.deleteByKey(id);
            return result == 0 ? ResponseBo.error("删除失败!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出核设施安全问题
     */
    @RequestMapping(value = "/exportEquipSecurity", method = RequestMethod.GET)
    public void exportSupervisor(@RequestParam(value = "equipDepartName", required = false) String equipDepartName,
                                  @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "serviceDepartName", required = false) String serviceDepartName,
                                 @RequestParam(value = "facName", required = false) String facName,
                                 @RequestParam(value = "checkTypeIds", required = false) String checkTypeIds,
                                 @RequestParam(value = "content", required = false) String content,
                                 @RequestParam(value = "start_date", required = false) String start_date,
                                 @RequestParam(value = "end_date", required = false) String end_date,
                                  @RequestParam(value = "questionTypeIds", required = false) String questionTypeIds,
                                  @RequestParam(value = "reformStatusTypeIds", required = false) String reformStatusTypeIds,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!equipDepartName.isEmpty()) {
            list.add(new Condition("equipDepartName", equipDepartName));
        }
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!serviceDepartName.isEmpty()) {
            list.add(new Condition("serviceDepartName", serviceDepartName));
        }
        if (!facName.isEmpty()) {
            list.add(new Condition("facName", facName));
        }
        if (!checkTypeIds.isEmpty()) {
            list.add(new Condition("checkLevelIds",  Stream.of(checkTypeIds).collect(toList())));
        }
        if (!content.isEmpty()) {
            list.add(new Condition("content", content));
        }
        if (!start_date.isEmpty()) {
            list.add(new Condition("start_date", DateUtils.GmtStringToDate(start_date)));
        }
        if (!end_date.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(end_date)));
        }
        if (!questionTypeIds.isEmpty()) {
            list.add(new Condition("questionTypeIds",  Stream.of(questionTypeIds).collect(toList())));
        }
        if (!reformStatusTypeIds.isEmpty()) {
            list.add(new Condition("reformStatusTypeIds",  Stream.of(reformStatusTypeIds).collect(toList())));
        }

        Page page = new Page();
        page.setConditions(list);

        equipSecurityService.exportEquipSecurity(page,response);

    }
}
