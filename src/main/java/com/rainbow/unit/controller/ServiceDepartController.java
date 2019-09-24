package com.rainbow.unit.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.ServiceDepartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by 13260 on 2019/5/11. 核设施运营单位管理
 */
@RestController
@RequestMapping("servicedepart")
public class ServiceDepartController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServiceDepartService serviceDepartService;

    /**
     * 添加核设施运营单位信息
     *
     * @param
     * @return
     */
    @PostMapping("/addServiceDepart")
    @SystemLog(description = "添加核设施运营单位信息")
    public ResponseBo add(@RequestBody ServiceDepart serviceDepart) {
        int result = serviceDepartService.addServiceDepart(serviceDepart);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施运营单位信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyServiceDepart")
    @SystemLog(description = "修改核设施运营单位信息")
    public ResponseBo modify(@RequestBody ServiceDepart serviceDepart) {

        int result = serviceDepartService.modifyServiceDepart(serviceDepart);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取核设施运营单位信息列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getServiceDepartList")
    public ResponseBo getServiceDepartList(@RequestBody Page page) {

        return serviceDepartService.getServiceDepartList(page);
    }

    /**
     * 获取核设施运营单位信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getServiceDepartById")
    public ResponseBo getServiceDepartById(String id) {
        return serviceDepartService.getServiceDepartById(id);
    }

    /**
     * 删除核设施运营单位信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteServiceDepartById")
    @SystemLog(description = "删除核设施运营单位信息")
    public ResponseBo deleteServiceDepartById(@RequestBody String id) {
        if (id != null) {
            int result = serviceDepartService.deleteServiceDepartById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 获取核设施运营单位信息列表
     *
     * @param
     * @return
     */
    @PostMapping("/getAllServiceDepartList")
    public ResponseBo getAllServiceDepartList() {
        List<ServiceDepart> result = serviceDepartService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @RequestMapping(value = "/exportServiceDepart", method = RequestMethod.GET)
    @SystemLog(description = "导出核设施运营单位信息")
    public void exportServiceDepart(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "groupIds", required = false) String groupIds, HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }

        if (!groupIds.isEmpty()) {

            list.add(new Condition("groupIds", Stream.of(groupIds).collect(toList())));
        }

        Page page = new Page();
        page.setConditions(list);

        serviceDepartService.exportServiceDepart(page, response);

    }

    @RequestMapping(value = "/importServiceDepart", method = RequestMethod.POST)
    @ResponseBody
    @SystemLog(description = "导入核设施运营单位信息")
    public ResponseBo importServiceDepart(HttpServletRequest request) {

        return serviceDepartService.importServiceDepart(request);
    }
}
