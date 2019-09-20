package com.rainbow.security.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.security.domain.EquipSecurity;
import com.rainbow.security.domain.FacSecurity;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface EquipSecurityService extends IService<EquipSecurity> {

    int addEquipSecurity(EquipSecurity equipSecurity);

    int modifyEquipSecurity(EquipSecurity equipSecurity);

    ResponseBo getEquipSecurityList(Page page);

    ResponseBo getEquipSecurityById(String id);

    void exportEquipSecurity(Page page,HttpServletResponse response);
}
