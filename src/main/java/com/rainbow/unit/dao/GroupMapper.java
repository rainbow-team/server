package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Group;

import java.util.List;
import java.util.Map;

public interface GroupMapper extends MyMapper<Group> {
    List<Group> getGroupList(Map<String,Object> map);
}