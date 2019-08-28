package com.rainbow.unit.controller;

import java.util.List;
import java.util.Map;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.UnitAddress;
import com.rainbow.unit.service.UnitAddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by idealks on 2019年8月21日16:52:59. 铀矿冶单位管理
 */
@RestController
@RequestMapping("unitaddress")
public class UnitAddressController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UnitAddressService service;

    @PostMapping("/addUnitAddress")
    public ResponseBo add(@RequestBody UnitAddress umine) {
        int result = service.addUnitAddress(umine);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    @PostMapping("/modifyUnitAddress")
    public ResponseBo modify(@RequestBody UnitAddress umine) {

        int result = service.modifyUnitAddress(umine);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    @PostMapping("/deleteUnitAddressById")
    public ResponseBo deleteUnitAddressByIds(@RequestBody String id) {
        if (id != null) {
            int result = service.deleteUnitAddressById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    @PostMapping("/getUnitAddressById")
    public ResponseBo getUnitAddressById(@RequestBody String id) {
        if (id != null) {
            UnitAddress result = service.selectByKey(id);
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }

    @PostMapping("/getAllUnitAddress")
    public ResponseBo getAllUnitAddress() {
        List<UnitAddress> result = service.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }

    @PostMapping("/getUnitAddressListByProvince")
    public ResponseBo getUnitAddressListByProvince(@RequestBody String province) {
        List<UnitAddress> result = service.getUnitAddressListByProvince(province);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }

    @PostMapping("/getChinaMapData")
    public ResponseBo getChinaMapData() {
        List<Map<String, String>> result = service.getChinaMapData();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
