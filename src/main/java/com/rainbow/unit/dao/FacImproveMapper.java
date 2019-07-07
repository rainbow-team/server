package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.FacImprove;

import java.util.List;
import java.util.Map;

public interface FacImproveMapper extends MyMapper<FacImprove> {
    List<FacImprove> getFacImproveList(Map<String,Object> map);

    void deleleFacImproveByFacId(String id);
}