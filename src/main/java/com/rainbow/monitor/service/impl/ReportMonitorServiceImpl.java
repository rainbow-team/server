package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.dao.ReportMonitorMapper;
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.ReportMonitorService;
import com.rainbow.monitor.service.WitnessMonitorService;
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
@Service("reportmonitorservice")
public class ReportMonitorServiceImpl extends BaseService<ReportMonitor> implements ReportMonitorService {

    @Autowired
    ReportMonitorMapper reportMonitorMapper;

    @Override
    @SystemLog(description="添加监督报告信息")
    public int addReportMonitor(ReportMonitor reportMonitor) {
        reportMonitor.setId(GuidHelper.getGuid());
        reportMonitor.setCreateDate(new Date());
        reportMonitor.setModifyDate(new Date());
        return reportMonitorMapper.insert(reportMonitor);
    }

    @Override
    @SystemLog(description="修改监督报告信息")
    public int modifyReportMonitor(ReportMonitor reportMonitor) {
        reportMonitor.setModifyDate(new Date());
        return reportMonitorMapper.updateByPrimaryKey(reportMonitor);
    }

    @Override
    public ResponseBo getReportMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ReportMonitor> list = reportMonitorMapper.getReportMonitorList(map);

        PageInfo<ReportMonitor> pageInfo = new PageInfo<ReportMonitor>(list);

        PagingEntity<ReportMonitor> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getReportMonitorById(String id) {
        ReportMonitor result = reportMonitorMapper.getReportMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}