package com.rainbow.permit.service;

import javax.servlet.http.HttpServletRequest;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.unit.domain.Umine;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacPermitService extends IService<FacPermit> {

    int addFacPermit(FacPermit facPermit);

    int modifyFacPermit(FacPermit facPermit);

    ResponseBo getFacPermitList(Page page);

    ResponseBo getFacPermitById(String id);

    ResponseBo importData(HttpServletRequest request);
}
