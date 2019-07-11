package com.rainbow.check.service;

import com.rainbow.check.domain.UmineMountainCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UmineMountainCheckService extends IService<UmineMountainCheck> {

    int addUmineMountainCheck(UmineMountainCheck umineMountainCheck);

    int modifyUmineMountainCheck(UmineMountainCheck umineMountainCheck);

    int deleteUmineMountainCheckById(String id);

    ResponseBo getUmineMountainCheckList(Page page);

    ResponseBo getUmineMountainCheckById(String id);
}
