package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.dao.CheckMonitorMapper;
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.service.CheckMonitorService;
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
@Service("witnessmonitorservice")
public class WitnessMonitorServiceImpl extends BaseService<WitnessMonitor> implements WitnessMonitorService {

    @Autowired
    WitnessMonitorMapper witnessMonitorMapper;

    @Override
    @SystemLog(description="添加监督见证信息")
    public int addWitnessMonitor(WitnessMonitor witnessMonitor) {
        witnessMonitor.setId(GuidHelper.getGuid());
        witnessMonitor.setCreateDate(new Date());
        witnessMonitor.setModifyDate(new Date());
        return witnessMonitorMapper.insert(witnessMonitor);
    }

    @Override
    @SystemLog(description="修改监督见证信息")
    public int modifyWitnessMonitor(WitnessMonitor witnessMonitor) {
        witnessMonitor.setModifyDate(new Date());
        return witnessMonitorMapper.updateByPrimaryKey(witnessMonitor);
    }

    @Override
    public ResponseBo getWitnessMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<WitnessMonitor> list = witnessMonitorMapper.getWitnessMonitorList(map);

        PageInfo<WitnessMonitor> pageInfo = new PageInfo<WitnessMonitor>(list);

        PagingEntity<WitnessMonitor> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getWitnessMonitorById(String id) {
        WitnessMonitor result = witnessMonitorMapper.getWitnessMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}