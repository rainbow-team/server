package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.ActivityPermitMapper;
import com.rainbow.permit.dao.EquipPermitMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.service.ActivityPermitService;
import com.rainbow.permit.service.EquipPermitService;
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
@Service("activitypermitservice")
public class ActivityPermitServiceImpl extends BaseService<ActivityPermit> implements ActivityPermitService {

    @Autowired
    ActivityPermitMapper activityPermitMapper;

    @Override
    public int addActivityPermit(ActivityPermit activityPermit) {
        activityPermit.setId(GuidHelper.getGuid());
        activityPermit.setCreateDate(new Date());
        activityPermit.setModifyDate(new Date());
        return activityPermitMapper.insert(activityPermit);
    }

    @Override
    public int modifyActivityPermit(ActivityPermit activityPermit) {
        activityPermit.setModifyDate(new Date());
        return activityPermitMapper.updateByPrimaryKey(activityPermit);
    }

    @Override
    public ResponseBo getActivityPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ActivityPermit> list = activityPermitMapper.getActivityPermitList(map);

        PageInfo<ActivityPermit> pageInfo = new PageInfo<ActivityPermit>(list);

        PagingEntity<ActivityPermit> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getActivityPermitById(String id) {
        ActivityPermit result = activityPermitMapper.getActivityPermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}