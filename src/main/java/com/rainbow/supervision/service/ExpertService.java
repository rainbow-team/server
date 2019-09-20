package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.Expert;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface ExpertService extends IService<Expert> {

    int addExpert(Expert expert);

    int modifyExpert(Expert expert);

    ResponseBo getExpertList(Page page);

    ResponseBo getExpertById(String id);

    void exportExpert(Page page,HttpServletResponse response);
}
