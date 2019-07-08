package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.UmineMountain;

import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UmineMountainService extends IService<UmineMountain> {

    int addUmineMountain(UmineMountain umineMountain);

    int modifyUmineMountain(UmineMountain umineMountain);

    int deleteUmineMountainById(String id);

    ResponseBo getUmineMountainList(Page page);

    ResponseBo getUmineMountainById(String id);
}
