package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.UmineMountainCheckMapper;
import com.rainbow.check.dao.UminePlaceCheckMapper;
import com.rainbow.check.domain.UmineMountainCheck;
import com.rainbow.check.domain.UminePlaceCheck;
import com.rainbow.check.service.UmineMountainCheckService;
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
@Service("uminemountaincheckservice")
public class UmineMountainCheckServiceImpl extends BaseService<UmineMountainCheck> implements UmineMountainCheckService {

    @Autowired
    UmineMountainCheckMapper umineMountainCheckMapper;

    @Override
    @SystemLog(description="添加铀矿山井下消防审查信息")
    public int addUmineMountainCheck(UmineMountainCheck umineMountainCheck) {
        umineMountainCheck.setId(GuidHelper.getGuid());
        umineMountainCheck.setCreateDate(new Date());
        umineMountainCheck.setModifyDate(new Date());
        return umineMountainCheckMapper.insert(umineMountainCheck);
    }

    @Override
    @SystemLog(description="修改铀矿山井下消防审查信息")
    public int modifyUmineMountainCheck(UmineMountainCheck umineMountainCheck) {
        umineMountainCheck.setModifyDate(new Date());
        return umineMountainCheckMapper.updateByPrimaryKey(umineMountainCheck);
    }

    @Override
    @SystemLog(description="删除铀矿山井下消防审查信息")
    public int deleteUmineMountainCheckById(String id) {
        Object result = umineMountainCheckMapper.getUmineMountainCheckRelationCount(id);
        if (result != null) {
            return umineMountainCheckMapper.deleteUmineMountainCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getUmineMountainCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineMountainCheck> list = umineMountainCheckMapper.getUmineMountainCheckList(map);

        PageInfo<UmineMountainCheck> pageInfo = new PageInfo<UmineMountainCheck>(list);

        PagingEntity<UmineMountainCheck> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineMountainCheckById(String id) {
        UmineMountainCheck result = umineMountainCheckMapper.getUmineMountainCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}