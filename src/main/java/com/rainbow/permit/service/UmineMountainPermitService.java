package com.rainbow.permit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.UmineMountainPermit;
import com.rainbow.permit.domain.UminePlacePermit;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UmineMountainPermitService extends IService<UmineMountainPermit> {

    int addUmineMountainPermit(UmineMountainPermit umineMountainPermit);

    int modifyUmineMountainPermit(UmineMountainPermit umineMountainPermit);

    ResponseBo getUmineMountainPermitList(Page page);

    ResponseBo getUmineMountainPermitById(String id);

    void exportUminemountainPermit(Page page, HttpServletResponse response);
}
