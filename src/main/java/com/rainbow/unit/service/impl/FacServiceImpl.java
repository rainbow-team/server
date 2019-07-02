package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.Group;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.GroupService;
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
@Service("facservice")
public class FacServiceImpl extends BaseService<Fac> implements FacService {


    @Autowired
    FacMapper facMapper;

    @Override
    public int addFac(Fac fac) {
        fac.setId(GuidHelper.getGuid());
        fac.setCreateDate(new Date());
        fac.setModifyDate(new Date());
        return facMapper.insert(fac);
    }

    @Override
    public int modifyFac(Fac fac) {
        fac.setModifyDate(new Date());
        return facMapper.updateByPrimaryKey(fac);
    }

    @Override
    public ResponseBo getFacList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Fac> list = facMapper.getFacList(map);

        PageInfo<Fac> pageInfo = new PageInfo<Fac>(list);

        PagingEntity<Fac> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
