package com.rainbow.config.controller;

import com.rainbow.common.controller.BaseController;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.config.domain.LinkDetail;
import com.rainbow.config.domain.SystemConfig;
import com.rainbow.config.service.LinkService;
import com.rainbow.config.service.SystemConfigService;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.service.ActivityPermitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/15 11:44
 * @Description:
 **/

@RestController
@RequestMapping("/link")
public class LinkController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LinkService linkService;

    /**
     * 添加链接信息
     *
     * @param
     * @return
     */
    @PostMapping("/addLink")
    public ResponseBo add(@RequestBody LinkDetail linkDetail) {
        int result = linkService.addLinkDetail(linkDetail);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改链接信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyLink")
    public ResponseBo modify(@RequestBody LinkDetail linkDetail) {

        int result = linkService.updateAll(linkDetail);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取所有链接信息
     *
     * @param
     * @return
     */
    @PostMapping("/getAllLinkList")
    public ResponseBo getAllLinkList() {

        return linkService.getAllLinkList();
    }

    /**
     * 获取链接信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getLinkDetailById")
    public ResponseBo getLinkDetailById(@RequestBody String id) {
        return ResponseBo.ok(linkService.selectByKey(id));
    }

    /**
     * 删除链接信息
     *
     * @param
     * @return
     */
    @PostMapping("/deleteLinkDetailById")
    public ResponseBo deleteLinkDetailById(@RequestBody String id) {
        return ResponseBo.ok(linkService.deleteByKey(id));
    }
}
