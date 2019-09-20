package com.rainbow.security.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.security.domain.FacSecurity;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacSecurityService extends IService<FacSecurity> {

    int addFacSecurity(FacSecurity facSecurity);

    int modifyFacSecurity(FacSecurity facSecurity);

    ResponseBo getFacSecurityList(Page page);

    ResponseBo getFacSecurityById(String id);

    void exportFacSecurity(Page page,HttpServletResponse response);

}
