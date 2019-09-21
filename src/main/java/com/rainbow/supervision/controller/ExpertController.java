package com.rainbow.supervision.controller;


import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.Expert;
import com.rainbow.supervision.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 核安全监督专家管理
 */
@RestController
@RequestMapping("supervisionexpert")
public class ExpertController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExpertService supervisionExportService;

    /**
     * 添加核安全监督专家信息
     *
     * @param
     * @return
     */
    @PostMapping("/addExpert")
    public ResponseBo add(@RequestBody Expert supervisionExpert) {
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
    public ResponseBo modify(@RequestBody Expert supervisionExpert) {

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
        supervisionExportService.batchDelete(ids,"id", Expert.class);
        return ResponseBo.ok();
    }

    /**
     * 导出安全生产培训信息
     */
    @RequestMapping(value = "/exportExpert", method = RequestMethod.GET)
    public void exportExpert( @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "major", required = false) String major,
                                    @RequestParam(value = "startAge", required = false) String startAge,
                                    @RequestParam(value = "endAge", required = false) String endAge,
                                    HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }
        if (!major.isEmpty()) {
            list.add(new Condition("major", major));
        }
        if (!startAge.isEmpty()) {
            list.add(new Condition("startAge", startAge));
        }
        if (!endAge.isEmpty()) {
            list.add(new Condition("endAge", endAge));
        }

        Page page = new Page();
        page.setConditions(list);

        supervisionExportService.exportExpert(page,response);

    }
}
