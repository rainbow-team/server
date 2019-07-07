package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.controller.domain.SupervisionSupervisorResponse;
import com.rainbow.supervision.domain.SupervisionSupervisor;

import java.util.List;
import java.util.Map;

public interface SupervisionSupervisorMapper extends MyMapper<SupervisionSupervisor> {

    List<SupervisionSupervisorResponse> getSupervisionSupervisorList(Map<String,Object> map);

}