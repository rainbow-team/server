package com.rainbow.permit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.EquipPermit;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface ActivityPermitService extends IService<ActivityPermit> {

    int addActivityPermit(ActivityPermit activityPermit);

    int modifyActivityPermit(ActivityPermit activityPermit);

    ResponseBo getActivityPermitList(Page page);

    ResponseBo getActivityPermitById(String id);

    void exportActivityPermit(Page page, HttpServletResponse response);

    ResponseBo importData(HttpServletRequest request);
}
