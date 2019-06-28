package com.rainbow.supervision.controller;


import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.domain.SupervisionMonitorTrain;
import com.rainbow.supervision.service.SupervisionMonitorTrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 */
@RestController("/supervisiontrain")
public class SupervisionMonitorTrainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisionMonitorTrainService supervisionMonitorTrainService;

    /**
     * 保存核安全监督培训信息
     *
     * @param supervisionMonitorTrain
     * @return
     */
    @PostMapping("/addTrainRecord")
    public ResponseBo add(@RequestBody SupervisionMonitorTrain supervisionMonitorTrain) {
        int result = supervisionMonitorTrainService.addTrainRecord(supervisionMonitorTrain);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全监督培训信息
     *
     * @param supervisionMonitorTrain
     * @return
     */
    @PostMapping("/modifyTrainRecord")
    public ResponseBo modify(@RequestBody SupervisionMonitorTrain supervisionMonitorTrain) {

        int result = supervisionMonitorTrainService.modifyTrainRecord(supervisionMonitorTrain);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 删除核安全监督培训信息
     *
     * @param supervisionMonitorTrain
     * @return
     */
    @PostMapping("/deleteTrainRecordById")
    public ResponseBo delete(@RequestBody SupervisionMonitorTrain supervisionMonitorTrain) {
        int result = supervisionMonitorTrainService.deleteByKey(supervisionMonitorTrain.getId());
        if (result == 1) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("删除失败");
        }
    }


    /**
     * 返回所有的培训记录信息
     * @return
     */
    @PostMapping("/getAllTrainRecords")
    public List<SupervisionMonitorTrain> selectAllTrainRecord() {

        List<SupervisionMonitorTrain> trainRecords = supervisionMonitorTrainService.selectAll();

        return trainRecords;
    }
}
