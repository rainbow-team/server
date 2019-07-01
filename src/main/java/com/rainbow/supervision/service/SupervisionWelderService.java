package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.domain.SupervisionWelder;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface SupervisionWelderService extends IService<SupervisionWelder> {

    int addWelder(SupervisionWelder welder);

    int modifyWelder(SupervisionWelder welder);

    ResponseBo getWelderList(Page page);
}
