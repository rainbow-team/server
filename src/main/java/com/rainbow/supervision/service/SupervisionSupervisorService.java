package com.rainbow.supervision.service;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.SupervisionSupervisor;

/**
 * Created by 13260 on 2019/5/11.
 */
public interface SupervisionSupervisorService extends IService<SupervisionSupervisor> {


    ResponseBo saveOrUpdateSupervisionSupervisor(SupervisionSupervisor supervisionSupervisor);

}
