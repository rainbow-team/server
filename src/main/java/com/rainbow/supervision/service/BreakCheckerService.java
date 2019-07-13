package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.BreakChecker;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface BreakCheckerService extends IService<BreakChecker> {

    int addBreakChecker(BreakChecker breakChecker);

    int modifyBreakChecker(BreakChecker breakChecker);

    ResponseBo getBreakCheckerList(Page page);

    ResponseBo getBreakCheckerById(String id);
}
