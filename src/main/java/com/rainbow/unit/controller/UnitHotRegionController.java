package com.rainbow.unit.controller;

import java.util.List;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.unit.domain.UnitHotRegion;
import com.rainbow.unit.service.UnitHotRegionService;

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
@RequestMapping("unithotregion")
public class UnitHotRegionController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UnitHotRegionService service;

    @PostMapping("/addUnitHotRegion")
    public ResponseBo add(@RequestBody UnitHotRegion region) {
        int result = service.addUnitHotRegion(region);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    @PostMapping("/modifyUnitHotRegion")
    public ResponseBo modify(@RequestBody UnitHotRegion region) {

        int result = service.modifyUnitHotRegion(region);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    @PostMapping("/deleteUnitHotRegionById")
    public ResponseBo deleteUnitHotRegionByIds(@RequestBody String id) {
        if (id != null) {
            int result = service.deleteUnitHotRegionById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    @PostMapping("/getAllUnitHotRegion")
    public ResponseBo getAllUnitHotRegion() {
        List<UnitHotRegion> result = service.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }

    @PostMapping("/insertRegionsBatch")
    public ResponseBo insertRegionsBatch(@RequestBody List<UnitHotRegion> regions) {
        int result = service.insertRegionsBatch(regions);

        if (result == regions.size()) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    @PostMapping("/getUnitHotRegionListByUnitId")
    public ResponseBo getUnitHotRegionListByUnitId(@RequestBody String unitId) {
        List<UnitHotRegion> result = service.getUnitHotRegionListByUnitId(unitId);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }

    @PostMapping("/getUnitHotRegionById")
    public ResponseBo getUnitHotRegionById(@RequestBody String id) {
        UnitHotRegion result = service.getUnitHotRegionById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }

    @PostMapping("getUnitHotRegionList")
    public ResponseBo getUnitHotRegionList(@RequestBody Page page) {
        return service.getUnitHotRegionList(page);
    }
}
