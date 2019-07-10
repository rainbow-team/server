package com.rainbow.permit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.UminePlacePermit;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UminePlacePermitService extends IService<UminePlacePermit> {

    int addUminePlacePermit(UminePlacePermit uminePlacePermit);

    int modifyUminePlacePermit(UminePlacePermit uminePlacePermit);

    ResponseBo getUminePlacePermitList(Page page);

    ResponseBo getUminePlacePermitById(String id);
}
