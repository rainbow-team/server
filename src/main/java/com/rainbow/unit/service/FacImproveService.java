package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.domain.GroupExtend;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacImproveService extends IService<FacImprove> {

    void deleteFacImprove(List<String> ids);

    ResponseBo getFacImproveList(Page page);



}
