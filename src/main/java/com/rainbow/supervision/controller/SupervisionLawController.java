package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisionLaw;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;
import com.rainbow.supervision.service.SupervisionLawService;
import com.rainbow.supervision.service.SupervisionMonitorTrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 监管法规管理
 */
@RestController
@RequestMapping("supervisionlaw")
public class SupervisionLawController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisionLawService supervisionLawService;

    /**
     * 保存监管法规信息
     *
     * @param
     * @return
     */
    @PostMapping("/addLaw")
    public ResponseBo add(@RequestBody SupervisionLaw supervisionLaw) {
        int result = supervisionLawService.addLaw(supervisionLaw);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改监管法规信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyLaw")
    public ResponseBo modify(@RequestBody SupervisionLaw supervisionLaw) {

        int result = supervisionLawService.modifyLaw(supervisionLaw);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取监管法规列表
     * @param page
     * @return
     */
    @PostMapping("/getLawList")
    public ResponseBo getLawList(@RequestBody Page page){

        return supervisionLawService.getLawList(page);
    }

    /**
     * 获取培训详情
     * @param id
     * @return
     */
    @GetMapping("/getLawById")
    public ResponseBo getLawById(String id){
        SupervisionLaw result =  supervisionLawService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除培训信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteLawByIds")
    public ResponseBo deleteLawByIds(@RequestBody List<String> ids){
        supervisionLawService.batchDelete(ids,"id",SupervisionLaw.class);
        return ResponseBo.ok();
    }
}
