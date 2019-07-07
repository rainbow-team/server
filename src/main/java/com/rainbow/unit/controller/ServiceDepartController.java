package com.rainbow.unit.controller;


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

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设施运营单位管理
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
     * @param page
     * @return
     */
    @PostMapping("/getServiceDepartList")
    public ResponseBo getServiceDepartList(@RequestBody Page page){

        return serviceDepartService.getServiceDepartList(page);
    }

    /**
     * 获取核设施运营单位信息详情
     * @param id
     * @return
     */
    @GetMapping("/getServiceDepartById")
    public ResponseBo getServiceDepartById(String id) {
        return serviceDepartService.getServiceDepartById(id);
    }

    /**
     * 删除核设施运营单位信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteEquipDepartByIds")
    public ResponseBo deleteServiceDepartByIds(@RequestBody List<String> ids){
        if ((ids!=null)&&(ids.size()>0)) {
            serviceDepartService.deleteServiceDepartByIds(ids);
        }
        return ResponseBo.ok();
    }
}
