package com.rainbow.check.controller;


import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.check.service.UminePlaceCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
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
    @SystemLog(description="添加铀尾矿(渣)库审评信息")
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
    @SystemLog(description="修改铀尾矿(渣)库审评信息")
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
    @PostMapping("/geUminePlaceCheckById")
    public ResponseBo getUminePlaceCheckById(@RequestBody String id) {
        return uminePlaceCheckService.getUminePlaceCheckById(id);
    }

    /**
     * 删除铀尾矿(渣)库审评信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteUminePlaceCheckById")
    @SystemLog(description="删除铀尾矿(渣)库审评信息")
    public ResponseBo deleteUminePlaceCheckById(@RequestBody String id) {
        if (id != null) {
            int result = uminePlaceCheckService.deleteUminePlaceCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出铀尾矿（渣）库审评信息
     */
    @RequestMapping(value = "/exportUmineplaceCheck", method = RequestMethod.GET)
    @SystemLog(description = "导出铀尾矿（渣）库审评信息")
    public void exportUmineplaceCheck(
            @RequestParam(value = "umineName", required = false) String umineName,
            @RequestParam(value = "uminePlaceName", required = false) String uminePlaceName,
            @RequestParam(value = "typeIds", required = false) String typeIds,
            @RequestParam(value = "stageIds", required = false) String stageIds,
            HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!umineName.isEmpty()) {
            list.add(new Condition("umineName", umineName));
        }
        if (!uminePlaceName.isEmpty()) {
            list.add(new Condition("uminePlaceName", uminePlaceName));
        }
        if (!typeIds.isEmpty()) {
            list.add(new Condition("typeIds", Stream.of(typeIds).collect(toList())));
        }
        if (!stageIds.isEmpty()) {
            list.add(new Condition("stageIds", Stream.of(stageIds).collect(toList())));
        }

        Page page = new Page();
        page.setConditions(list);

        uminePlaceCheckService.exportUmineplaceCheck(page, response);

    }
}
