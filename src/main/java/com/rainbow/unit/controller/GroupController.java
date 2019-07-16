package com.rainbow.unit.controller;


import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 * 集团信息管理
 */
@RestController
@RequestMapping("group")
public class GroupController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GroupService groupService;

    /**
     * 添加集团信息
     *
     * @param
     * @return
     */
    @PostMapping("/addGroup")
    public ResponseBo add(@RequestBody Group group) {
        int result = groupService.addGroup(group);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改集团信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyGroup")
    public ResponseBo modify(@RequestBody Group group) {

        int result = groupService.modifyGroup(group);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    /**
     * 获取集团信息列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getGroupList")
    public ResponseBo getGroupList(@RequestBody Page page) {

        return groupService.getGroupList(page);
    }

    /**
     * 获取集团信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getGroupById")
    public ResponseBo getGroupById(String id) {
        return groupService.getGroupById(id);
    }

    /**
     * 删除集团信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteGroupById")
    public ResponseBo deleteGroupById(@RequestBody String id) {
        if (id != null) {
            int result = groupService.deleteGroupById(id);
            return result == 0 ? ResponseBo.error("存在关联，不允许删除!") : ResponseBo.ok("删除成功");
        }
        return ResponseBo.ok();
    }

    /**
     * 获取所有的集团信息
     *
     * @param
     * @return
     */
    @PostMapping("/getAllGroup")
    public ResponseBo getAllGroup() {

        List<Group> result = groupService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
