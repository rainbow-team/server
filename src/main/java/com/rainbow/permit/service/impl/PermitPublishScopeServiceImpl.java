package com.rainbow.permit.service.impl;

import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.permit.dao.PermitPublishScopeMapper;
import com.rainbow.permit.domain.PermitPublishScope;
import com.rainbow.permit.service.PermitPublishScopeService;
import com.rainbow.system.domain.SystemMenu;
import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: rainbow
 * @description: 许可信息知悉范围管理类
 * @author: daiyy
 * @create: 2021-05-12 20:26
 **/
@Service("permitPublishScopeService")
public class PermitPublishScopeServiceImpl extends BaseService<PermitPublishScope> implements PermitPublishScopeService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PermitPublishScopeMapper mapper;

    @Override
    public int add(List<PermitPublishScope> permits) {
        return mapper.add(permits);
    }

    @Override
    public int deleteByPermitId(String infoId) {

        return mapper.deleteByPermitId(infoId);
    }

    @Override
    public ResponseBo getListByPermitId(String permitId) {
        List<PermitPublishScope> result = mapper.getListByPermitId(permitId);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
