package com.rainbow.unit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.FacReport;
import com.rainbow.unit.domain.FacReportExtend;

import java.util.List;
import java.util.Map;

public interface FacReportMapper extends MyMapper<FacReport> {

    List<FacReport> getFacReportList(Map<String,Object> map);

    FacReport getFacReportById(String id);

}