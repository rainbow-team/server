package com.rainbow.check.service;

import com.rainbow.check.domain.UmineMountainFileCheck;
import com.rainbow.check.domain.UminePlaceFileCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UmineMountainFileCheckService extends IService<UmineMountainFileCheck> {

    int addUmineMountainFileCheck(UmineMountainFileCheck umineMountainFileCheck);

    ResponseBo getUmineMountainFileCheckList(Page page);

    ResponseBo getUmineMountainFileCheckById(String id);
}
