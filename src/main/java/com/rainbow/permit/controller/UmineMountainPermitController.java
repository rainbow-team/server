package com.rainbow.permit.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.domain.UmineMountainPermit;
import com.rainbow.permit.domain.UminePlacePermit;
import com.rainbow.permit.service.UmineMountainPermitService;
import com.rainbow.permit.service.UminePlacePermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 铀矿山井下消防许可信息管理
 */
@RestController
@RequestMapping("uminemountainpermit")
public class UmineMountainPermitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineMountainPermitService umineMountainPermitService;

    /**
     * 添加铀矿山井下消防许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmineMountainPermit")
    @SystemLog(description="添加铀矿山井下消防许可信息")
    public ResponseBo add(@RequestBody UmineMountainPermit umineMountainPermit) {
        int result = umineMountainPermitService.addUmineMountainPermit(umineMountainPermit);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀矿山井下消防许可信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmineMountainPermit")
    @SystemLog(description="修改铀矿山井下消防许可信息")
    public ResponseBo modify(@RequestBody UmineMountainPermit umineMountainPermit) {

        int result = umineMountainPermitService.modifyUmineMountainPermit(umineMountainPermit);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀矿山井下消防许可信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUmineMountainPermitList")
    public ResponseBo getUmineMountainPermitList(@RequestBody Page page) {

        return umineMountainPermitService.getUmineMountainPermitList(page);
    }

    /**
     * 获取铀矿山井下消防许可详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getUmineMountainPermitById")
    public ResponseBo getUmineMountainPermitById(String id) {
        return umineMountainPermitService.getUmineMountainPermitById(id);
    }

    /**
     * 删除铀矿山井下消防许可信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteUmineMountainPermitByIds")
    @SystemLog(description="删除铀矿山井下消防许可信息")
    public ResponseBo deleteUmineMountainPermitByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            umineMountainPermitService.batchDelete(ids, "id", UmineMountainPermit.class);
        }
        return ResponseBo.ok();
    }
}
