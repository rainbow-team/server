package com.rainbow.supervision.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.Supervisor;
import com.rainbow.unit.domain.Umine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 13260 on 2019/5/11.
 */
public interface SupervisorService extends IService<Supervisor> {

    int addSupervisor(Supervisor supervisor);

    int modifySupervisor(Supervisor supervisor);

    ResponseBo deleteSupervisorById(String id);

    ResponseBo getSupervisorList(Page page);

    ResponseBo getSupervisorById(String id);

    void exportSupervisor(Page page,HttpServletResponse response);

    ResponseBo importSupervisor(HttpServletRequest request);
}
