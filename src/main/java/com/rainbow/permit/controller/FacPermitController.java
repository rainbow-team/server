package com.rainbow.permit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.service.FacPermitService;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.UminePlaceImprove;
import com.rainbow.unit.service.UmineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核设施许可信息管理
 */
@RestController
@RequestMapping("facpermit")
public class FacPermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FacPermitService facPermitService;

    /**
     * 添加核设施许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addFacPermit")
    public ResponseBo add(@RequestBody FacPermit facPermit) {
        int result = facPermitService.addFacPermit(facPermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核设施许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyFacPermit")
    public ResponseBo modify(@RequestBody FacPermit facPermit) {

        int result = facPermitService.modifyFacPermit(facPermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核设施许可信息列表
     * @param page
     * @return
     */
    @PostMapping("/getFacPermitList")
    public ResponseBo getFacPermitList(@RequestBody Page page){

        return facPermitService.getFacPermitList(page);
    }

    /**
     * 获取核设施许可信息详情
     * @param id
     * @return
     */
    @GetMapping("/getFacPermitById")
    public ResponseBo getFacPermitById(String id){
        return facPermitService.getFacPermitById(id);
    }

    /**
     * 删除核设施许可信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteFacPermitByIds")
    public ResponseBo deleteFacPermitByIds(@RequestBody List<String> ids){
        if ((ids != null) && (ids.size() > 0)) {
            facPermitService.batchDelete(ids,"id",FacPermit.class);
        }
        return ResponseBo.ok();
    }
}
