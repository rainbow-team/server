package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionLawMapper;
import com.rainbow.supervision.domain.SupervisionLaw;
import com.rainbow.supervision.service.LawSupervisionService;
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
@Service("SupervisionLawService")
public class SupervisionLawServiceImpl extends BaseService<SupervisionLaw> implements LawSupervisionService {

    @Autowired
    SupervisionLawMapper supervisionLawMapper;

    @Override
    public int addLaw(SupervisionLaw law) {
        law.setId(GuidHelper.getGuid());
        law.setCreateDate(new Date());
        law.setModifyDate(new Date());
        return supervisionLawMapper.insert(law);
    }

    @Override
    public int modifyLaw(SupervisionLaw law) {
        law.setModifyDate(new Date());
        return supervisionLawMapper.updateByPrimaryKey(law);
    }

    @Override
    public ResponseBo getLawList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionLaw> list = supervisionLawMapper.getLawList(map);

        PageInfo<SupervisionLaw> pageInfo = new PageInfo<SupervisionLaw>(list);

        PagingEntity<SupervisionLaw> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
