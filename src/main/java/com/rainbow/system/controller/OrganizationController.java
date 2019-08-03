package com.rainbow.system.controller;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.system.domain.Organization;
import com.rainbow.system.service.OrganizationService;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by idealks on 2019/7/30.
 */
@RestController
@RequestMapping("org")
public class OrganizationController {

    // private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OrganizationService organizationService;

    /**
     * 添加组织信息
     *
     * @param
     * @return
     */
    @PostMapping("/addOrganization")
    public ResponseBo add(@RequestBody Organization Organization) {
        int result = organizationService.addOrganization(Organization);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改组织信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyOrganization")
    public ResponseBo modify(@RequestBody Organization Organization) {

        int result = organizationService.modifyOrganization(Organization);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取组织信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getOrganizationList")
    public ResponseBo getOrganizationList(@RequestBody Page page) {

        return organizationService.getOrganizationList(page);
    }

    /**
     * 获取组织信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getOrganizationById")
    public ResponseBo getOrganizationById(String id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * 删除组织信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteOrganizationByIds")
    public ResponseBo deleteOrganizationByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            organizationService.batchDelete(ids, "id", Organization.class);
        }
        return ResponseBo.ok();
    }

    @PostMapping("/getAllOrganization")
    public ResponseBo getAllOrganization() {

        List<Organization> result = organizationService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
