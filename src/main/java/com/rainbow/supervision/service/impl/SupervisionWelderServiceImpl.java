package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionExpertMapper;
import com.rainbow.supervision.dao.SupervisionWelderMapper;
import com.rainbow.supervision.domain.SupervisionExpert;
import com.rainbow.supervision.domain.SupervisionWelder;
import com.rainbow.supervision.service.SupervisionExportService;
import com.rainbow.supervision.service.SupervisionWelderService;
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
@Service("SupervisionWelderService")
public class SupervisionWelderServiceImpl extends BaseService<SupervisionWelder> implements SupervisionWelderService {

    @Autowired
    SupervisionWelderMapper supervisionWelderMapper;

    @Override
    public int addWelder(SupervisionWelder welder) {
        welder.setId(GuidHelper.getGuid());
        welder.setCreateDate(new Date());
        welder.setModifyDate(new Date());
        return supervisionWelderMapper.insert(welder);
    }

    @Override
    public int modifyWelder(SupervisionWelder welder) {
        welder.setModifyDate(new Date());
        return supervisionWelderMapper.updateByPrimaryKey(welder);
    }

    @Override
    public ResponseBo getWelderList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionWelder> list = supervisionWelderMapper.getWelderList(map);

        PageInfo<SupervisionWelder> pageInfo = new PageInfo<SupervisionWelder>(list);

        PagingEntity<SupervisionWelder> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

}
