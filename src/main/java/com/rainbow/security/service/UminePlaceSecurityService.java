package com.rainbow.security.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.UminePlaceSecurity;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UminePlaceSecurityService extends IService<UminePlaceSecurity> {

    int addUminePlaceSecurity(UminePlaceSecurity uminePlaceSecurity);

    int modifyUminePlaceSecurity(UminePlaceSecurity uminePlaceSecurity);

    ResponseBo getUminePlaceSecurityList(Page page);

    ResponseBo getUminePlaceSecurityById(String id);
}
