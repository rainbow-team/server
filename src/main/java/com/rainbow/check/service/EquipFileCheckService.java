package com.rainbow.check.service;

import com.rainbow.check.domain.EquipFileCheck;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface EquipFileCheckService extends IService<EquipFileCheck> {

    int addEquipFileCheck(EquipFileCheck equipFileCheck);

    ResponseBo getEquipFileCheckList(Page page);

    ResponseBo getEquipFileCheckById(String id);
}
