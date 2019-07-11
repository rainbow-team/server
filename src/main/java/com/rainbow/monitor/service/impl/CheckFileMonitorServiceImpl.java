package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.ActivityFileCheckMapper;
import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.service.ActivityFileCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.dao.CheckFileMonitorMapper;
import com.rainbow.monitor.domain.CheckFileMonitor;
import com.rainbow.monitor.service.CheckFileMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("checkfilemonitorservice")
public class CheckFileMonitorServiceImpl extends BaseService<CheckFileMonitor> implements CheckFileMonitorService {

    @Autowired
    CheckFileMonitorMapper checkFileMonitorMapper;


    @Override
    public int addCheckFileMonitor(CheckFileMonitor activityFileCheck) {
        activityFileCheck.setId(GuidHelper.getGuid());
        return checkFileMonitorMapper.insert(activityFileCheck);
    }

    @Override
    public ResponseBo getCheckFileMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<CheckFileMonitor> list = checkFileMonitorMapper.getCheckFileMonitorList(map);

        PageInfo<CheckFileMonitor> pageInfo = new PageInfo<CheckFileMonitor>(list);

        PagingEntity<CheckFileMonitor> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getCheckFileMonitorById(String id) {
        CheckFileMonitor result = checkFileMonitorMapper.getCheckFileMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

}
