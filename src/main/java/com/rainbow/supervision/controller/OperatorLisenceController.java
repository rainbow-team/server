package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.OperatorLisence;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.supervision.service.OperatorLisenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 研究堆操纵员执照管理
 */
@RestController
@RequestMapping("operatorlisence")
public class OperatorLisenceController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OperatorLisenceService operatorLisenceService;

    /**
     * 添加研究堆操作员执照信息
     *
     * @param
     * @return
     */
    @PostMapping("/addOperatorLisence")
    public ResponseBo add(@RequestBody OperatorLisence operatorLisence) {
        int result = operatorLisenceService.addOperatorLisence(operatorLisence);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改研究堆操作员执照信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyOperatorLisence")
    public ResponseBo modify(@RequestBody OperatorLisence operatorLisence) {

        int result = operatorLisenceService.modifyOperatorLisence(operatorLisence);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取研究堆操作员执照信息列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getOperatorLisenceList")
    public ResponseBo getOperatorLisenceList(@RequestBody Page page) {

        return operatorLisenceService.getOperatorLisenceList(page);
    }

    /**
     * 获取研究堆操纵员执照详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getOperatorLisenceById")
    public ResponseBo getOperatorLisenceById(String id) {
        return operatorLisenceService.getOperatorLisenceById(id);
    }

    /**
     * 删除研究堆操作员执照信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteOperatorLisenceByIds")
    public ResponseBo deleteOperatorLisenceByIds(@RequestBody List<String> ids) {
        int result = operatorLisenceService.batchDelete(ids, "id", OperatorLisence.class);
        return result == 0 ? ResponseBo.error("删除失败") : ResponseBo.ok("删除成功");
    }
}
