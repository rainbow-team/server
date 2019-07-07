package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.UmineMountainImprove;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UmineMountainImproveService extends IService<UmineMountainImprove> {

    void deleteUmineMountainImprove(List<String> ids);

    ResponseBo getUmineMountainImproveList(Page page);



}
