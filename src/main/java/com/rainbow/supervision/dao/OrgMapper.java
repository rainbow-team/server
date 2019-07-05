package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.Org;

import java.util.List;
import java.util.Map;

public interface OrgMapper extends MyMapper<Org> {
    List<Org> getOrgList(Map<String,Object> map);

    Org getOrgById(String id);
}