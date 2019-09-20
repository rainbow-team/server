package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionLaw;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface LawSupervisionService extends IService<SupervisionLaw> {

    int addLaw(SupervisionLaw law);

    int modifyLaw(SupervisionLaw law);

    ResponseBo getLawList(Page page);

    void exportLaw(Page page,HttpServletResponse response);
}
