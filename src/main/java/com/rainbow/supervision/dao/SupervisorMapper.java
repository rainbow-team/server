package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.controller.domain.SupervisionSupervisorResponse;
import com.rainbow.supervision.domain.Supervisor;
import com.rainbow.supervision.domain.extend.SupervisorExtend;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SupervisorMapper extends MyMapper<Supervisor> {

    List<SupervisorExtend> getSupervisorList(Map<String,Object> map);

    SupervisorExtend getSupervisorById(String id);

    int updateExpireDateById(String id, Date expireDate);

    int getSupervisorByIdentity(String identity);

}