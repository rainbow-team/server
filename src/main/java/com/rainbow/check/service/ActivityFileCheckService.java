package com.rainbow.check.service;

import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.domain.EquipFileCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface ActivityFileCheckService extends IService<ActivityFileCheck> {

    int addActivityFileCheck(ActivityFileCheck activityFileCheck);

    ResponseBo getActivityFileCheckList(Page page);

    ResponseBo getActivityFileCheckById(String id);
}
