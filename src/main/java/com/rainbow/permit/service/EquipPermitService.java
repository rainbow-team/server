package com.rainbow.permit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.domain.FacPermit;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface EquipPermitService extends IService<EquipPermit> {

    int addEquipPermit(EquipPermit equipPermit);

    int modifyEquipPermit(EquipPermit equipPermit);

    ResponseBo getEquipPermitList(Page page);

    ResponseBo getEquipPermitById(String id);
}
