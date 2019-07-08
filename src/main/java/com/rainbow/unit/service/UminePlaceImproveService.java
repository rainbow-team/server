package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.UminePlaceImprove;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UminePlaceImproveService extends IService<UminePlaceImprove> {

    int addPlaceImprove(UminePlaceImprove uminePlaceImprove);

    ResponseBo getUminePlaceImproveList(Page page);

}
