package com.rainbow.supervision.controller;

import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Condition;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.util.DateUtils;
import com.rainbow.supervision.domain.OperatorLisence;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.OrgService;
import com.rainbow.supervision.service.SastindService;
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
 * 授权监管机构管理
 */
@RestController
@RequestMapping("org")
public class OrgController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrgService orgService;

    /**
     * 新增授权监管机构信息
     *
     * @param
     * @return
     */
    @PostMapping("/addOrg")
    @SystemLog(description="新增授权监管机构信息")
    public ResponseBo add(@RequestBody Org org) {
        int result = orgService.addOrg(org);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改授权监管机构信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyOrg")
    @SystemLog(description="修改授权监管机构信息")
    public ResponseBo modify(@RequestBody Org org) {
        int result = orgService.modifyOrg(org);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 删除授权监管机构信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteOrgByIds")
    @SystemLog(description="删除授权监管机构信息")
    public ResponseBo deleteOrgByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            orgService.deleteOrgByIds(ids);
        }
        return ResponseBo.ok();
    }

    /**
     * 获取授权监管机构列表
     * @param page
     * @return
     */
    @PostMapping("/getOrgList")
    public ResponseBo getOrgList(@RequestBody Page page){

        return orgService.getOrgList(page);
    }

    /**
     * 获取授权监管机构详情
     * @param id
     * @return
     */
    @GetMapping("/getOrgById")
    public ResponseBo getOrgById(String id){
        return orgService.getOrgById(id);
    }

    /**
     * 获取授权监管机构值和名称
     * @param
     * @return
     */
    @PostMapping("/getAllOrgList")
    public ResponseBo geAlltOrgList(){

        List<Org> list= orgService.selectAll();
        return ResponseBo.ok(list);
    }


    /**
     * 导出授权监管机构信息
     */
    @RequestMapping(value = "/exportOrg", method = RequestMethod.GET)
    @SystemLog(description="导出授权监管机构信息")
    public void exportOrg( @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "natureIds", required = false) String natureIds,
                                  @RequestParam(value = "leader", required = false) String leader,
                                  HttpServletResponse response) {

        List<Condition> list = new ArrayList<>();
        if (!name.isEmpty()) {
            list.add(new Condition("name", name));
        }

        if (!natureIds.isEmpty()) {
            list.add(new Condition("natureIds",  Stream.of(natureIds).collect(toList())));
        }

        if (!leader.isEmpty()) {
            list.add(new Condition("leader", leader));
        }


        Page page = new Page();
        page.setConditions(list);

        orgService.exportOrg(page,response);

    }
}
