package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.dao.ServiceAnnualReportMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.GroupExtend;
import com.rainbow.unit.domain.ServiceAnnualReport;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.service.GroupService;
import com.rainbow.unit.service.ServiceAnnualReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("serviceannualreport")
public class ServiceAnnualReportServiceImpl extends BaseService<ServiceAnnualReport> implements ServiceAnnualReportService {

    @Autowired
    ServiceAnnualReportMapper annualReportMapper;

    @Autowired
    FileInfoService fileInfoService;
    @Override
    public int addServiceAnnualReport(ServiceAnnualReport annualReport) {
        annualReport.setReportId(GuidHelper.getGuid());

        fileInfoService.updateFileInfoByIds(annualReport.getAttachmentList(),annualReport.getReportId());
        return  annualReportMapper.insert(annualReport);

    }

    @Override
    public int deleteReportByServiceId(String id) {
        return annualReportMapper.deleteReportsByServiceId(id);
        //return 0;
    }

    @Override
    public ResponseBo getServiceAnnualReportList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ServiceAnnualReport> list = annualReportMapper.getServiceAnnualReportList(map);

        PageInfo<ServiceAnnualReport> pageInfo = new PageInfo<ServiceAnnualReport>(list);

        PagingEntity<ServiceAnnualReport> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
