package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.FacReportMapper;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.FacReport;
import com.rainbow.unit.service.FacImproveService;
import com.rainbow.unit.service.FacReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("facreportservice")
public class FacReportServiceImpl extends BaseService<FacReport> implements FacReportService {


    @Autowired
    FacReportMapper facReportMapper;

    @Override
    public void deleteFacReport(List<String> ids) {
        super.batchDelete(ids,"id",FacReport.class);
    }

    @Override
    public ResponseBo getFacReportList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacReport> list = facReportMapper.getFacReportList(map);

        PageInfo<FacReport> pageInfo = new PageInfo<FacReport>(list);

        PagingEntity<FacReport> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public FacReport getFacReportById(String id) {
        return facReportMapper.getFacReportById(id);
        //return null;
    }

}
