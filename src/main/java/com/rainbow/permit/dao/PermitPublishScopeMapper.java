package com.rainbow.permit.dao;

import com.rainbow.common.config.MyMapper;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.permit.domain.PermitPublishScope;

import java.util.List;

/**
 * @program: rainbow
 * @description: PermitPublishScope mapper
 * @author: daiyy
 * @create: 2021-05-12 20:36
 **/
public interface PermitPublishScopeMapper extends MyMapper<PermitPublishScope> {
    int add(List<PermitPublishScope> permits);

    int deleteByPermitId(String infoId);

    List<PermitPublishScope> getListByPermitId(String permitId);
}
