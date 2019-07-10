package com.rainbow.check.service;

import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.EquipCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface ActivityCheckService extends IService<ActivityCheck> {

    int addActivityCheck(ActivityCheck activityCheck);

    int modifyActivityCheck(ActivityCheck activityCheck);

    int deleteActivityCheckById(String id);

    ResponseBo getActivityCheckList(Page page);

    ResponseBo getActivityCheckById(String id);
}
