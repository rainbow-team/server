package com.rainbow.permit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.service.ActivityPermitService;
import com.rainbow.permit.service.UminePlacePermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀尾矿(渣)库许可信息管理
 */
@RestController
@RequestMapping("umineplacepermit")
public class UminePlacePermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UminePlacePermitService uminePlacePermitService;

    /**
     * 添加铀尾矿(渣)库许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUminePlacePermit")
    public ResponseBo add(@RequestBody UminePlacePermit uminePlacePermit) {
        int result = uminePlacePermitService.addUminePlacePermit(uminePlacePermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀尾矿(渣)库许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUminePlacePermit")
    public ResponseBo modify(@RequestBody UminePlacePermit uminePlacePermit) {

        int result = uminePlacePermitService.modifyUminePlacePermit(uminePlacePermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀尾矿(渣)库许可信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUminePlacePermitList")
    public ResponseBo getUminePlacePermitList(@RequestBody Page page) {

        return uminePlacePermitService.getUminePlacePermitList(page);
    }

    /**
     * 获取铀尾矿(渣)库许可信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getUminePlacePermitById")
    public ResponseBo getUminePlacePermitById(String id) {
        return uminePlacePermitService.getUminePlacePermitById(id);
    }

    /**
     * 删除铀尾矿(渣)库许可信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteUminePlacePermitByIds")
    public ResponseBo deleteUminePlacePermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            uminePlacePermitService.batchDelete(ids, "id", UminePlacePermit.class);
        }
        return ResponseBo.ok();
    }
}
