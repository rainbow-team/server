package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;
import com.rainbow.supervision.domain.SupervisionProduceTrain;
import com.rainbow.supervision.service.SupervisionMonitorTrainService;
import com.rainbow.supervision.service.SupervisionProduceTrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 */
@RestController
@RequestMapping("supervisionProduceTrain")
public class SupervisionProduceTrainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisionProduceTrainService supervisionProduceTrainService;

    /**
     * 保存核安全监督培训信息
     *
     * @param
     * @return
     */
    @PostMapping("/addTrainRecord")
    public ResponseBo add(@RequestBody SupervisionProduceTrain supervisionProduceTrain) {
        int result = supervisionProduceTrainService.addTrainRecord(supervisionProduceTrain);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全监督培训信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyTrainRecord")
    public ResponseBo modify(@RequestBody SupervisionProduceTrain supervisionProduceTrain) {

        int result = supervisionProduceTrainService.modifyTrainRecord(supervisionProduceTrain);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取培训列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getTrainRecordList")
    public ResponseBo getTrainRecordList(@RequestBody Page page) {

        return supervisionProduceTrainService.getTrainRecordList(page);
    }

    /**
     * 获取培训详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getTrainRecordById")
    public ResponseBo getTrainRecordById(String id) {
        SupervisionProduceTrain result = supervisionProduceTrainService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除培训信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteTrainRecordByIds")
    public ResponseBo deleteTrainRecordByIds(@RequestBody List<String> ids) {
        supervisionProduceTrainService.batchDelete(ids, "id", SupervisionProduceTrain.class);
        return ResponseBo.ok();
    }
}