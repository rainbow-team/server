package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.ServiceDepart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface ServiceDepartService extends IService<ServiceDepart> {

    int addServiceDepart(ServiceDepart serviceDepart);

    int modifyServiceDepart(ServiceDepart serviceDepart);

    int deleteServiceDepartById(String id);

    ResponseBo getServiceDepartList(Page page);

    ResponseBo getServiceDepartById(String id);

    void exportServiceDepart(Page page, HttpServletResponse response);

    ResponseBo importServiceDepart(HttpServletRequest request);
}
