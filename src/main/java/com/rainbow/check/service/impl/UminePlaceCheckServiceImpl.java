package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.ActivityCheckMapper;
import com.rainbow.check.dao.UminePlaceCheckMapper;
import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.check.service.UminePlaceCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
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
@Service("umineplacecheckservice")
public class UminePlaceCheckServiceImpl extends BaseService<UminePlaceCheck> implements UminePlaceCheckService {

    @Autowired
    UminePlaceCheckMapper uminePlaceCheckMapper;

    @Override
    public int addUminePlaceCheck(UminePlaceCheck uminePlaceCheck) {
        uminePlaceCheck.setId(GuidHelper.getGuid());
        uminePlaceCheck.setCreateDate(new Date());
        uminePlaceCheck.setModifyDate(new Date());
        return uminePlaceCheckMapper.insert(uminePlaceCheck);
    }

    @Override
    public int modifyUminePlaceCheck(UminePlaceCheck uminePlaceCheck) {
        uminePlaceCheck.setModifyDate(new Date());
        return uminePlaceCheckMapper.updateByPrimaryKey(uminePlaceCheck);
    }

    @Override
    public int deleteUminePlaceCheckById(String id) {
        Object result = uminePlaceCheckMapper.getUminePlaceCheckRelationCount(id);
        if (result != null) {
            return uminePlaceCheckMapper.deleteUminePlaceCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getUminePlaceCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceCheck> list = uminePlaceCheckMapper.getUminePlaceCheckList(map);

        PageInfo<UminePlaceCheck> pageInfo = new PageInfo<UminePlaceCheck>(list);

        PagingEntity<UminePlaceCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlaceCheckById(String id) {
        UminePlaceCheck result = uminePlaceCheckMapper.getUminePlaceCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}