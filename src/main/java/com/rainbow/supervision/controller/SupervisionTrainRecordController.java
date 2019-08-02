package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisorTrainRecord;
import com.rainbow.supervision.service.SupervisorTrainRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核安全监督员培训记录管理
 */
@RestController
@RequestMapping("SupervisionTrainRecord")
public class SupervisionTrainRecordController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisorTrainRecordService trainRecordService;

    /**
     * 保存核安全监督员培训记录
     *
     * @param
     * @return
     */
    @PostMapping("/addTrainRecord")
    public ResponseBo add(@RequestBody SupervisorTrainRecord record) {
        int result = trainRecordService.addSupervisionTrainRecord(record);
       // trainRecordService.updateSupervisorExpireDate(record);
        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全监督员培训记录
     *
     * @param
     * @return
     */
    @PostMapping("/modifyTrainRecord")
    public ResponseBo modify(@RequestBody SupervisorTrainRecord record) {

        int result = trainRecordService.updateAll(record);
        //trainRecordService.updateSupervisorExpireDate(record);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核安全监督员培训记录列表
     * @param page
     * @return
     */
    @PostMapping("/getTrainRecordList")
    public ResponseBo getTrainRecordList(@RequestBody Page page){

        return trainRecordService.getSupervisionTrainRecordList(page);
    }
    /*

        */
/**
     * 获取法规详情
     * @param id
     * @return
     */    /*

    @GetMapping("/getLawById")
    public ResponseBo getLawById(String id){
        SupervisionLaw result =  supervisionLawService.selectByKey(id);
        return ResponseBo.ok(result);
    }
    */

    /**
     * 删除核安全监督员培训记录
     * @param ids
     * @return
     */
    @PostMapping("/deleteTrainRecordByIds")
    public ResponseBo deleteTrainRecordByIds(@RequestBody List<String> ids){
        trainRecordService.batchDelete(ids,"id",SupervisorTrainRecord.class);
        return ResponseBo.ok();
    }
}
