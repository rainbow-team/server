package com.rainbow.supervision.service.impl;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.supervision.dao.SupervisionSupervisorMapper;
import com.rainbow.supervision.domain.SupervisionSupervisor;
import com.rainbow.supervision.service.SupervisionSupervisorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 13260 on 2019/5/11.
 */
@Service("supervisionSupervisorService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SupervisionSupervisorServiceImpl extends BaseService<SupervisionSupervisor> implements SupervisionSupervisorService{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SupervisionSupervisorMapper supervisionSupervisorMapper;

}
