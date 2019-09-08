package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.common.util.StrUtil;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.extend.OrgExtend;

import java.util.List;
import java.util.Map;

public interface OrgMapper extends MyMapper<Org> {

    List<OrgExtend> getOrgList(Map<String,Object> map);

    OrgExtend getOrgById(String id);

    String getOrgIdByName(String name);
}