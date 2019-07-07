package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.service.SupervisionExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核安全监督专家管理
 */
@RestController
@RequestMapping("supervisionexpert")
public class SupervisionExpertController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SupervisionExpertService supervisionExportService;

    /**
     * 添加核安全监督专家信息
     *
     * @param
     * @return
     */
    @PostMapping("/addExpert")
    public ResponseBo add(@RequestBody SupervisionExpert supervisionExpert) {
        int result = supervisionExportService.addExpert(supervisionExpert);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改核安全监督专家信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyExpert")
    public ResponseBo modify(@RequestBody SupervisionExpert supervisionExpert) {

        int result = supervisionExportService.modifyExpert(supervisionExpert);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取核安全监督专家列表
     * @param page
     * @return
     */
    @PostMapping("/getExpertList")
    public ResponseBo getExpertList(@RequestBody Page page){

        return supervisionExportService.getExpertList(page);
    }

    /**
     * 获取核安全监督专家详情
     * @param id
     * @return
     */
    @GetMapping("/getExpertById")
    public ResponseBo getLawById(String id){
        return supervisionExportService.getExpertById(id);
    }

    /**
     * 删除和安全监督专家信息
     * @param ids
     * @return
     */
    @PostMapping("/deleteExpertByIds")
    public ResponseBo deleteLawByIds(@RequestBody List<String> ids){
        supervisionExportService.batchDelete(ids,"id",SupervisionExpert.class);
        return ResponseBo.ok();
    }
}
