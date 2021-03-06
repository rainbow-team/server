package com.rainbow.supervision.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.SupervisorTrain;
import com.rainbow.supervision.service.SupervisorTrainService;
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
    @SystemLog(description="保存核安全监督培训信息")
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
    @SystemLog(description="修改核安全监督培训信息")
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
    @SystemLog(description="删除核安全监督培训信息")
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
    @SystemLog(description="删除核安全监督培训信息")
    public ResponseBo deleteMonitorTrainByIds(@RequestBody List<String> ids){
        supervisorTrainService.batchDelete(ids,"id",SupervisorTrain.class);
        return ResponseBo.ok();
    }

    /**
     * 导出核安全监督培训信息
     */
    @RequestMapping(value = "/exportMonitorTrain", method = RequestMethod.GET)
    @SystemLog(description="导出核安全监督培训信息")
    public void exportMonitorTrain( @RequestParam(value = "batch", required = false) String batch,
                                  @RequestParam(value = "beginDate", required = false) String beginDate,
                                  @RequestParam(value = "endDate", required = false) String endDate,
                                  @RequestParam(value = "place", required = false) String place,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!batch.isEmpty()) {
            list.add(new Condition("batch", batch));
        }
        if (!beginDate.isEmpty()) {
            list.add(new Condition("beginDate", DateUtils.GmtStringToDate(beginDate)));
        }
        if (!endDate.isEmpty()) {
            list.add(new Condition("endDate", DateUtils.GmtStringToDate(endDate)));
        }
        if (!place.isEmpty()) {
            list.add(new Condition("place", place));
        }

        Page page = new Page();
        page.setConditions(list);

        supervisorTrainService.exportMonitorTrain(page,response);

    }
}
