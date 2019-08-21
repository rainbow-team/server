package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisorTrain;
import com.rainbow.supervision.service.SupervisorTrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 */
@RestController
@RequestMapping("supervisiontrain")
public class SupervisorTrainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisorTrainService supervisorTrainService;

    /**
     * 保存核安全监督培训信息
     *
     * @param supervisionMonitorTrain
     * @return
     */
    @PostMapping("/addMonitorTrain")
    public ResponseBo add(@RequestBody SupervisorTrain supervisionMonitorTrain) {
        int result = supervisorTrainService.addMonitorTrain(supervisionMonitorTrain);
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
    @PostMapping("/modifyMonitorTrain")
    public ResponseBo modify(@RequestBody SupervisorTrain supervisionMonitorTrain) {

        int result = supervisorTrainService.modifyMonitorTrain(supervisionMonitorTrain);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 删除核安全监督培训信息
     *
     * @param
     * @return
     */
    @PostMapping("/deleteMonitorTrainById")
    public ResponseBo delete(@RequestBody String id) {
        return supervisorTrainService.deleteMonitorTrainById(id);
    }


    /**
     * 返回所有的培训记录信息
     * @return
     */
    @PostMapping("/getAllMonitorTrain")
    public List<SupervisorTrain> selectAllTrainRecord() {

        List<SupervisorTrain> trainRecords = supervisorTrainService.selectAll();
        return trainRecords;
    }

    /**
     * 获取培训列表
     * @param page
     * @return
     */
    @PostMapping("/getMonitorTrainList")
    public ResponseBo getMonitorTrainList(@RequestBody Page page){
        return supervisorTrainService.getMonitorTrainList(page);
    }

    /**
     * 获取培训详情
     * @param id
     * @return
     */
    @GetMapping("/getMonitorTrainById")
    public ResponseBo getMonitorTrainById(String id){
        return supervisorTrainService.getMonitorTrainById(id);
    }

    /**
     * 删除培训信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteMonitorTrainByIds")
    public ResponseBo deleteMonitorTrainByIds(@RequestBody List<String> ids){
        supervisorTrainService.batchDelete(ids,"id",SupervisorTrain.class);
        return ResponseBo.ok();
    }
}
