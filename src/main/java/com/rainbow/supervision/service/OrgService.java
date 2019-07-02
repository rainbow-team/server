package com.rainbow.supervision.service;

import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.Org;
import com.rainbow.supervision.domain.SupervisionSastind;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:10
 * @Description:
 **/
public interface OrgService extends IService<Org> {

    int addOrg(Org org);

    int modifyOrg(Org org);

}
