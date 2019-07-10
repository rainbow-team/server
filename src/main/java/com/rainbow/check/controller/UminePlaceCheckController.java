package com.rainbow.check.controller;


import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.check.service.UminePlaceCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 13260 on 2019/5/11.
 * 铀尾矿(渣)库审评信息管理
 */
@RestController
@RequestMapping("umineplacecheck")
public class UminePlaceCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UminePlaceCheckService uminePlaceCheckService;

    /**
     * 添加铀尾矿(渣)库审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUminePlaceCheck")
    public ResponseBo add(@RequestBody UminePlaceCheck uminePlaceCheck) {
        int result = uminePlaceCheckService.addUminePlaceCheck(uminePlaceCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀尾矿(渣)库审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUminePlaceCheck")
    public ResponseBo modify(@RequestBody UminePlaceCheck uminePlaceCheck) {

        int result = uminePlaceCheckService.modifyUminePlaceCheck(uminePlaceCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀尾矿(渣)库审评信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUminePlaceCheckList")
    public ResponseBo getUminePlaceCheckList(@RequestBody Page page) {

        return uminePlaceCheckService.getUminePlaceCheckList(page);
    }

    /**
     * 获取铀尾矿(渣)库审评信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/geUminePlaceCheckById")
    public ResponseBo getUminePlaceCheckById(String id) {
        return uminePlaceCheckService.getUminePlaceCheckById(id);
    }

    /**
     * 删除铀尾矿(渣)库审评信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteUminePlaceCheckById")
    public ResponseBo deleteUminePlaceCheckById(String id) {
        if (id != null) {
            int result = uminePlaceCheckService.deleteUminePlaceCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }
}
