package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.BreakCheckerMapper;
import com.rainbow.supervision.dao.OperatorLisenceMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.OperatorLisence;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.supervision.service.OperatorLisenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("operatorLisenceService")
public class OperatorLisenceServiceImpl extends BaseService<OperatorLisence> implements OperatorLisenceService {

    @Autowired
    OperatorLisenceMapper operatorLisenceMapper;

    @Override
    public int addOperatorLisence(OperatorLisence operatorLisence) {
        operatorLisence.setId(GuidHelper.getGuid());
        operatorLisence.setCreateDate(new Date());
        operatorLisence.setModifyDate(new Date());
        return operatorLisenceMapper.insert(operatorLisence);
    }

    @Override
    public int modifyOperatorLisence(OperatorLisence operatorLisence) {
        operatorLisence.setModifyDate(new Date());
        return operatorLisenceMapper.updateByPrimaryKey(operatorLisence);
    }

    @Override
    public ResponseBo getOperatorLisenceList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<OperatorLisence> list = operatorLisenceMapper.getOperatorLisenceList(map);

        PageInfo<OperatorLisence> pageInfo = new PageInfo<OperatorLisence>(list);

        PagingEntity<OperatorLisence> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getOperatorLisenceById(String id) {
        OperatorLisence operatorLisence = operatorLisenceMapper.getOperatorLisenceById(id);
        return ResponseBo.ok(operatorLisence);
    }
}
