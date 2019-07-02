package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.Group;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface GroupService extends IService<Group> {

    int addGroup(Group group);

    int modifyGroup(Group group);

    ResponseBo getGroupList(Page page);
}
