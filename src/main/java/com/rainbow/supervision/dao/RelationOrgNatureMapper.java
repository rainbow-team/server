package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.RelationOrgNature;

public interface RelationOrgNatureMapper extends MyMapper<RelationOrgNature> {

    void deleteRelationByOrgId(String orgId);
}