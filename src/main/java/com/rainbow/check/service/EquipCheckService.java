package com.rainbow.check.service;

import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface EquipCheckService extends IService<EquipCheck> {

    int addEquipCheck(EquipCheck facCheck);

    int modifyEquipCheck(EquipCheck facCheck);

    int deleteEquipCheckById(String id);

    ResponseBo getEquipCheckList(Page page);

    ResponseBo getEquipCheckById(String id);
}
