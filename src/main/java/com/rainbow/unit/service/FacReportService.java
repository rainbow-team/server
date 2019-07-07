package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.FacReport;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacReportService extends IService<FacReport> {

    void deleteFacReport(List<String> ids);

    ResponseBo getFacReportList(Page page);

    FacReport getFacReportById(String id);



}
