package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.domain.SupervisionWelder;
import com.rainbow.supervision.service.SupervisionExpertService;
import com.rainbow.supervision.service.SupervisionWelderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 焊接人员资质管理
 */
@RestController
@RequestMapping("welder")
public class SupervisionWelderController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisionWelderService supervisionWelderService;

    /**
     * 添加焊接人员资质信息
     *
     * @param
     * @return
     */
    @PostMapping("/addWelder")
    public ResponseBo add(@RequestBody SupervisionWelder supervisionWelder) {
        int result = supervisionWelderService.addWelder(supervisionWelder);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改焊接人员资质信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyWelder")
    public ResponseBo modify(@RequestBody SupervisionWelder supervisionWelder) {

        int result = supervisionWelderService.modifyWelder(supervisionWelder);

        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取焊接人员资质列表
     * @param page
     * @return
     */
    @PostMapping("/getWelderList")
    public ResponseBo getWelderList(@RequestBody Page page){

        return supervisionWelderService.getWelderList(page);
    }

    /**
     * 获取焊接人员资质详情
     * @param id
     * @return
     */
    @GetMapping("/getWelderById")
    public ResponseBo getWelderById(String id) {
        SupervisionWelder welder = supervisionWelderService.selectByKey(id);
        return ResponseBo.ok(welder);
    }

    /**
     * 删除焊接人员资质信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteWelderByIds")
    public ResponseBo deleteWelderByIds(@RequestBody List<String> ids){
        if ((ids != null) && (ids.size() > 0)) {
            supervisionWelderService.batchDelete(ids,"id",SupervisionWelder.class);
            ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }
}
