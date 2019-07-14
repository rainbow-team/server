package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionSastindMapper;
import com.rainbow.supervision.domain.SupervisionSastind;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.supervision.service.SastindService;
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
@Service("SupervisionSastindService")
public class SastindServiceImpl extends BaseService<SupervisionSastind> implements SastindService {

    @Autowired
    SupervisionSastindMapper sastindMapper;
    /**
     * 保存国防科工局信息
     * @param
     * @return
     */
    @Override
    public int addSastind(SupervisionSastind sastind) {
      sastind.setId(GuidHelper.getGuid());
      sastind.setCreateDate(new Date());
      sastind.setModifyDate(new Date());
      return sastindMapper.insert(sastind);
    }

    /**
     * 修改国防科工局信息
     * @param sastind
     * @return
     */
    @Override
    public int modifySastind(SupervisionSastind sastind) {
        sastind.setModifyDate(new Date());
        return sastindMapper.updateByPrimaryKey(sastind);
    }

    @Override
    public ResponseBo getSastindList(Page page){
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisionSastind> list = sastindMapper.getSastindList(map);

        PageInfo<SupervisionSastind> pageInfo = new PageInfo<SupervisionSastind>(list);

        PagingEntity<SupervisionSastind> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }
}
