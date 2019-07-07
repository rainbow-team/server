package com.rainbow.supervision.controller;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.OperatorLisence;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.service.OrgService;
import com.rainbow.supervision.service.SastindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 授权监管机构管理
 */
@RestController("org")
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

}
