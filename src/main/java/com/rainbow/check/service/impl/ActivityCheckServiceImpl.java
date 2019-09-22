package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.ActivityCheckMapper;
import com.rainbow.check.dao.EquipCheckMapper;
import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
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
@Service("activitycheckservice")
public class ActivityCheckServiceImpl extends BaseService<ActivityCheck> implements ActivityCheckService {

    @Autowired
    ActivityCheckMapper activityCheckMapper;

    @Override
    public int addActivityCheck(ActivityCheck activityCheck) {
        activityCheck.setId(GuidHelper.getGuid());
        activityCheck.setCreateDate(new Date());
        activityCheck.setModifyDate(new Date());
        return activityCheckMapper.insert(activityCheck);
    }

    @Override
    public int modifyActivityCheck(ActivityCheck activityCheck) {
        activityCheck.setModifyDate(new Date());
        return activityCheckMapper.updateByPrimaryKey(activityCheck);
    }

    @Override
    public int deleteActivityCheckById(String id) {
        Object result = activityCheckMapper.getActivityCheckRelationCount(id);
        if (result != null) {
            return activityCheckMapper.deleteActivityCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getActivityCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ActivityCheck> list = activityCheckMapper.getActivityCheckList(map);

        PageInfo<ActivityCheck> pageInfo = new PageInfo<ActivityCheck>(list);

        PagingEntity<ActivityCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getActivityCheckById(String id) {
        ActivityCheck result = activityCheckMapper.getActivityCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}