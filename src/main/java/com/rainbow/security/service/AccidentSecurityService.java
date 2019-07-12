package com.rainbow.security.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface AccidentSecurityService extends IService<AccidentSecurity> {

    int addAccidentSecurity(AccidentSecurity accidentSecurity);

    int modifyAccidentSecurity(AccidentSecurity accidentSecurity);

    ResponseBo getAccidentSecurityList(Page page);

    ResponseBo getAccidentSecurityById(String id);
}
