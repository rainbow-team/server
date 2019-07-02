package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.BreakCheckerMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("groupservice")
public class GroupServiceImpl extends BaseService<Group> implements GroupService {


    @Autowired
    GroupMapper groupMapper;

    @Override
    public int addGroup(Group group) {
        group.setId(GuidHelper.getGuid());
        group.setCreateDate(new Date());
        group.setModifyDate(new Date());
        return groupMapper.insert(group);
    }

    @Override
    public int modifyGroup(Group group) {
        group.setModifyDate(new Date());
        return groupMapper.updateByPrimaryKey(group);
    }

    @Override
    public ResponseBo getGroupList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Group> list = groupMapper.getGroupList(map);

        PageInfo<Group> pageInfo = new PageInfo<Group>(list);

        PagingEntity<Group> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
