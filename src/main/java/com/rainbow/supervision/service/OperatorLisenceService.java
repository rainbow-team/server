package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.OperatorLisence;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface OperatorLisenceService extends IService<OperatorLisence> {

    int addOperatorLisence(OperatorLisence operatorLisence);

    int modifyOperatorLisence(OperatorLisence operatorLisence);

    ResponseBo getOperatorLisenceList(Page page);

    ResponseBo getOperatorLisenceById(String id);

    void exportOperator(Page page,HttpServletResponse response);
}
