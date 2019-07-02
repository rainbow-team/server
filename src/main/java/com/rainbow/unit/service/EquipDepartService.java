package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Fac;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface EquipDepartService extends IService<EquipDepart> {

    int addEquipDepart(EquipDepart equipDepart);

    int modifyEquipDepart(EquipDepart equipDepart);

    ResponseBo getEquipDepartList(Page page);
}
