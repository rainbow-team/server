package com.rainbow.check.service;

import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.ActivityPermit;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacFileCheckService extends IService<FacFileCheck> {

    int addFacFileCheck(FacFileCheck facFileCheck);

    ResponseBo getFacFileCheckList(Page page);

    ResponseBo getFacFileCheckById(String id);
}
