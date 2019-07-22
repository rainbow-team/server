package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.ActivityCheckMapper;
import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.dao.CheckMonitorMapper;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.service.CheckMonitorService;
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
@Service("checkmonitorservice")
public class CheckMonitorServiceImpl extends BaseService<CheckMonitor> implements CheckMonitorService {

    @Autowired
    CheckMonitorMapper checkMonitorMapper;

    @Override
    public int addCheckMonitor(CheckMonitor checkMonitor) {
        checkMonitor.setId(GuidHelper.getGuid());
        checkMonitor.setCreateDate(new Date());
        checkMonitor.setModifyDate(new Date());
        return checkMonitorMapper.insert(checkMonitor);
    }

    @Override
    public int modifyCheckMonitor(CheckMonitor checkMonitor) {
        checkMonitor.setModifyDate(new Date());
        return checkMonitorMapper.updateByPrimaryKey(checkMonitor);
    }

    @Override
    public int deleteCheckMonitorById(String id) {
        Object result = checkMonitorMapper.getCheckMonitorRelationCount(id);
        if (result != null) {
            return checkMonitorMapper.deleteCheckMonitorById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getCheckMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<CheckMonitor> list = checkMonitorMapper.getCheckMonitorList(map);

        PageInfo<CheckMonitor> pageInfo = new PageInfo<CheckMonitor>(list);

        PagingEntity<CheckMonitor> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getCheckMonitorById(String id) {
        CheckMonitor result = checkMonitorMapper.getCheckMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}