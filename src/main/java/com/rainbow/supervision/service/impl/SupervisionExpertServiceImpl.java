package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionExpertMapper;
import com.rainbow.supervision.dao.SupervisionLawMapper;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.domain.SupervisionLaw;
import com.rainbow.supervision.service.SupervisionExportService;
import com.rainbow.supervision.service.SupervisionLawService;
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
@Service("SupervisionExpertService")
public class SupervisionExpertServiceImpl extends BaseService<SupervisionExpert> implements SupervisionExportService {

    @Autowired
    SupervisionExpertMapper supervisionExpertMapper;

    @Override
    public int addExpert(SupervisionExpert expert) {
        expert.setId(GuidHelper.getGuid());
        expert.setCreateDate(new Date());
        expert.setModifyDate(new Date());
        return supervisionExpertMapper.insert(expert);
    }

    @Override
    public int modifyExpert(SupervisionExpert expert) {
        expert.setModifyDate(new Date());
        return supervisionExpertMapper.updateByPrimaryKey(expert);
    }

    @Override
    public ResponseBo getExpertList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionExpert> list = supervisionExpertMapper.getExpertList(map);

        PageInfo<SupervisionExpert> pageInfo = new PageInfo<SupervisionExpert>(list);

        PagingEntity<SupervisionExpert> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
