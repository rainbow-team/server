package com.rainbow.supervision.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.supervision.domain.SupervisorTrainRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SupervisionTrainRecordMapper extends MyMapper<SupervisorTrainRecord> {

    List<SupervisorTrainRecord> getTrainRecordList(Map<String,Object> map);

    Date getMaxExpireDateBySupervisorId(String supervisorId);

    int getRecordCountByClassId(String classId);

    int getRecordCountBySupervisorId(String supervisorId);
}