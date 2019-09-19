package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.check.dao.ActivityFileCheckMapper;
import com.rainbow.check.dao.EquipFileCheckMapper;
import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.domain.EquipFileCheck;
import com.rainbow.check.service.ActivityFileCheckService;
import com.rainbow.check.service.EquipFileCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("activityfilecheckservice")
public class ActivityFileCheckServiceImpl extends BaseService<ActivityFileCheck> implements ActivityFileCheckService {

    @Autowired
    ActivityFileCheckMapper activityFileCheckMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    @SystemLog(description="添加核活动及其他审评信息审评文件")
    public int addActivityFileCheck(ActivityFileCheck activityFileCheck) {
        activityFileCheck.setId(GuidHelper.getGuid());
        fileInfoService.updateFileInfoByIds(activityFileCheck.getAttachmentList(),activityFileCheck.getId());
        return activityFileCheckMapper.insert(activityFileCheck);
    }

    @Override
    public ResponseBo getActivityFileCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ActivityFileCheck> list = activityFileCheckMapper.getActivityFileCheckList(map);

        PageInfo<ActivityFileCheck> pageInfo = new PageInfo<ActivityFileCheck>(list);

        PagingEntity<ActivityFileCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getActivityFileCheckById(String id) {
        ActivityFileCheck result = activityFileCheckMapper.getActivityFileCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

}
