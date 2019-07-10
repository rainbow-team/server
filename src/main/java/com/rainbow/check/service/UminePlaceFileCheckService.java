package com.rainbow.check.service;

import com.rainbow.check.domain.ActivityFileCheck;
import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UminePlaceFileCheckService extends IService<UminePlaceFileCheck> {

    int addUminePlaceFileCheck(UminePlaceFileCheck uminePlaceFileCheck);

    ResponseBo getUminePlaceFileCheckList(Page page);

    ResponseBo getUminePlaceFileCheckById(String id);
}
