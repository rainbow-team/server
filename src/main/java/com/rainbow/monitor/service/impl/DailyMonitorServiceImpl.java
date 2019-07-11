package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.dao.DailyMonitorMapper;
import com.rainbow.monitor.domain.DailyMonitor;
import com.rainbow.monitor.service.DailyMonitorService;
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
@Service("dailymonitorservice")
public class DailyMonitorServiceImpl extends BaseService<DailyMonitor> implements DailyMonitorService {

    @Autowired
    DailyMonitorMapper dailyMonitorMapper;

    @Override
    public int addDailyMonitor(DailyMonitor activityCheck) {
        activityCheck.setId(GuidHelper.getGuid());
        activityCheck.setCreateDate(new Date());
        activityCheck.setModifyDate(new Date());
        return dailyMonitorMapper.insert(activityCheck);
    }

    @Override
    public int modifyDailyMonitor(DailyMonitor activityCheck) {
        activityCheck.setModifyDate(new Date());
        return dailyMonitorMapper.updateByPrimaryKey(activityCheck);
    }

    @Override
    public ResponseBo getDailyMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<DailyMonitor> list = dailyMonitorMapper.getDailyMonitorList(map);

        PageInfo<DailyMonitor> pageInfo = new PageInfo<DailyMonitor>(list);

        PagingEntity<DailyMonitor> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getDailyMonitorById(String id) {
        DailyMonitor result = dailyMonitorMapper.getDailyMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}