package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.domain.SupervisionLaw;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface SupervisionExpertService extends IService<SupervisionExpert> {

    int addExpert(SupervisionExpert expert);

    int modifyExpert(SupervisionExpert expert);

    ResponseBo getExpertList(Page page);

    ResponseBo getExpertById(String id);
}
