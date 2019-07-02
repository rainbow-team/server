package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.Group;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface FacService extends IService<Fac> {

    int addFac(Fac fac);

    int modifyFac(Fac fac);

    ResponseBo getFacList(Page page);
}
