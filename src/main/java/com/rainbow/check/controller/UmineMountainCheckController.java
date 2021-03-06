package com.rainbow.check.controller;


import com.rainbow.check.domain.UmineMountainCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.check.service.UmineMountainCheckService;
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
 * 铀矿山井下消防审评信息管理
 */
@RestController
@RequestMapping("uminemountaincheck")
public class UmineMountainCheckController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UmineMountainCheckService umineMountainCheckService;

    /**
     * 添加铀矿山井下消防审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/addUmineMountainCheck")
    @SystemLog(description="添加铀矿山井下消防审评信息")
    public ResponseBo add(@RequestBody UmineMountainCheck umineMountainCheck) {
        int result = umineMountainCheckService.addUmineMountainCheck(umineMountainCheck);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改铀矿山井下消防审评信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUmineMountainCheck")
    @SystemLog(description="修改铀矿山井下消防审查信息")
    public ResponseBo modify(@RequestBody UmineMountainCheck umineMountainCheck) {

        int result = umineMountainCheckService.modifyUmineMountainCheck(umineMountainCheck);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取铀矿山井下消防审评信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getUmineMountainCheckList")
    public ResponseBo getUmineMountainCheckList(@RequestBody Page page) {

        return umineMountainCheckService.getUmineMountainCheckList(page);
    }

    /**
     * 获取铀矿山井下消防审评信息详情
     *
     * @param id
     * @return
     */
    @PostMapping("/geUmineMountainCheckById")
    public ResponseBo getUmineMountainCheckById(@RequestBody String id) {
        return umineMountainCheckService.getUmineMountainCheckById(id);
    }

    /**
     * 删除铀矿山井下消防审评信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteUmineMountainCheckById")
    @SystemLog(description="删除铀矿山井下消防审查信息")
    public ResponseBo deleteUmineMountainCheckById(@RequestBody String id) {
        if (id != null) {
            int result = umineMountainCheckService.deleteUmineMountainCheckById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 导出铀矿山井下消防审查信息
     */
    @RequestMapping(value = "/exportUminemountainCheck", method = RequestMethod.GET)
    @SystemLog(description = "导出铀矿山井下消防审查信息")
    public void exportUminemountainCheck(
            @RequestParam(value = "umineName", required = false) String umineName,
            @RequestParam(value = "umineMountainName", required = false) String umineMountainName,
            @RequestParam(value = "content", required = false) String content,
            HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!umineName.isEmpty()) {
            list.add(new Condition("umineName", umineName));
        }
        if (!umineMountainName.isEmpty()) {
            list.add(new Condition("umineMountainName", umineMountainName));
        }
        if (!content.isEmpty()) {
            list.add(new Condition("content", content));
        }

        Page page = new Page();
        page.setConditions(list);

        umineMountainCheckService.exportUminemountainCheck(page, response);

    }
}
