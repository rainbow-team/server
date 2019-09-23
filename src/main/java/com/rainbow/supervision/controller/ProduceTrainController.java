package com.rainbow.supervision.controller;


import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.SupervisionProduceTrain;
import com.rainbow.supervision.service.ProduceTrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 安全生产培训信息管理
 */
@RestController
@RequestMapping("producetrain")
public class ProduceTrainController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProduceTrainService supervisionProduceTrainService;

    /**
     * 保存安全生产培训信息
     *
     * @param
     * @return
     */
    @PostMapping("/addProduceTrainRecord")
    @SystemLog(description="保存安全生产培训信息")
    public ResponseBo add(@RequestBody SupervisionProduceTrain supervisionProduceTrain) {
        int result = supervisionProduceTrainService.addProduceTrainRecord(supervisionProduceTrain);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改安全生产培训信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyProduceTrainRecord")
    @SystemLog(description="修改安全生产培训信息")
    public ResponseBo modify(@RequestBody SupervisionProduceTrain supervisionProduceTrain) {

        int result = supervisionProduceTrainService.modifyProduceTrainRecord(supervisionProduceTrain);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取培训详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getProduceTrainRecordById")
    public ResponseBo getTrainRecordById(String id) {
        SupervisionProduceTrain result = supervisionProduceTrainService.selectByKey(id);
        return ResponseBo.ok(result);
    }

    /**
     * 删除安全生产培训
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteProduceTrainRecordByIds")
    @SystemLog(description="删除安全生产培训")
    public ResponseBo deleteTrainRecordByIds(@RequestBody List<String> ids) {
        supervisionProduceTrainService.batchDelete(ids, "id", SupervisionProduceTrain.class);
        return ResponseBo.ok();
    }

    /**
     * 获取培训列表
     * @param page
     * @return
     */
    @PostMapping("/getProduceTrainList")
    public ResponseBo getProduceTrainList(@RequestBody Page page){
        return supervisionProduceTrainService.getProduceTrainRecordList(page);
    }

    /**
     * 导出安全生产培训信息
     */
    @RequestMapping(value = "/exportProduceTrain", method = RequestMethod.GET)
    @SystemLog(description="导出安全生产培训信息")
    public void exportProduceTrain( @RequestParam(value = "batch", required = false) String batch,
                                    @RequestParam(value = "begin_date", required = false) String beginDate,
                                    @RequestParam(value = "end_date", required = false) String endDate,
                                    @RequestParam(value = "place", required = false) String place,
                                    HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!batch.isEmpty()) {
            list.add(new Condition("batch", batch));
        }
        if (!beginDate.isEmpty()) {
            list.add(new Condition("begin_date", DateUtils.GmtStringToDate(beginDate)));
        }
        if (!endDate.isEmpty()) {
            list.add(new Condition("end_date", DateUtils.GmtStringToDate(endDate)));
        }
        if (!place.isEmpty()) {
            list.add(new Condition("place", place));
        }

        Page page = new Page();
        page.setConditions(list);

        supervisionProduceTrainService.exportProduceTrain(page,response);

    }
}
