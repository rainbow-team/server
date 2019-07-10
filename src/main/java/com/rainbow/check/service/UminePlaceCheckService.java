package com.rainbow.check.service;

import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UminePlaceCheckService extends IService<UminePlaceCheck> {

    int addUminePlaceCheck(UminePlaceCheck uminePlaceCheck);

    int modifyUminePlaceCheck(UminePlaceCheck uminePlaceCheck);

    int deleteUminePlaceCheckById(String id);

    ResponseBo getUminePlaceCheckList(Page page);

    ResponseBo getUminePlaceCheckById(String id);
}
