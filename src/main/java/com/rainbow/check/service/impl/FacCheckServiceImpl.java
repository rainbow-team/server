package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.FacCheckMapper;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.service.FacCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.permit.dao.ActivityPermitMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.service.ActivityPermitService;
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
@Service("faccheckservice")
public class FacCheckServiceImpl extends BaseService<FacCheck> implements FacCheckService {

    @Autowired
    FacCheckMapper facCheckMapper;

    @Override
    @SystemLog(description="添加核设施审评信息")
    public int addFacCheck(FacCheck facCheck) {
        facCheck.setId(GuidHelper.getGuid());
        facCheck.setCreateDate(new Date());
        facCheck.setModifyDate(new Date());
        return facCheckMapper.insert(facCheck);
    }

    @Override
    @SystemLog(description="修改核设施审评信息")
    public int modifyFacCheck(FacCheck facCheck) {
        facCheck.setModifyDate(new Date());
        return facCheckMapper.updateByPrimaryKey(facCheck);
    }

    @Override
    @SystemLog(description="删除核设施审评信息")
    public int deleteFacCheckById(String id) {
        Object result = facCheckMapper.getFacCheckRelationCount(id);
        if (result != null) {
            return facCheckMapper.deleteFacCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getFacCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacCheck> list = facCheckMapper.getFacCheckList(map);

        PageInfo<FacCheck> pageInfo = new PageInfo<FacCheck>(list);

        PagingEntity<FacCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacCheckById(String id) {
        FacCheck result = facCheckMapper.getFacCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}