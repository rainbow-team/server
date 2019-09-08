package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.FacExtend;
import com.rainbow.unit.domain.Group;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacService extends IService<Fac> {

    int addFac(Fac fac);

    int modifyFac(Fac fac);

    int deleteFacById(String id);

    ResponseBo getFacList(Page page);

    ResponseBo getFacById(String id);

    ResponseBo getFacListByServiceId(String serviceId);

    void exportFac(Page page, HttpServletResponse response);
}
