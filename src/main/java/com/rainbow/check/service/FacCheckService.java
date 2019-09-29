package com.rainbow.check.service;

import com.rainbow.check.domain.FacCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.ActivityPermit;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacCheckService extends IService<FacCheck> {

    int addFacCheck(FacCheck facCheck);

    int modifyFacCheck(FacCheck facCheck);

    int deleteFacCheckById(String id);

    ResponseBo getFacCheckList(Page page);

    ResponseBo getFacCheckById(String id);

    void exportFacCheck(Page page, HttpServletResponse response);
}
