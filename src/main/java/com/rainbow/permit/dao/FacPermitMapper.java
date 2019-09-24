package com.rainbow.permit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.unit.domain.Umine;
import com.rainbow.unit.domain.UmineExtend;

import java.util.List;
import java.util.Map;

public interface FacPermitMapper extends MyMapper<FacPermit> {

    List<FacPermit> getFacPermitList(Map<String, Object> map);

    FacPermit getFacPermitById(String id);

    int verifyDuplication(Map<String, Object> params);
}