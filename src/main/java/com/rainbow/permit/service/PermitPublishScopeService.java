package com.rainbow.permit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.permit.domain.PermitPublishScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: rainbow
 * @description: 333
 * @author: daiyy
 * @create: 2021-05-12 17:01
 **/
public interface PermitPublishScopeService extends IService<PermitPublishScope> {

    int add(List<PermitPublishScope> permits);

    int deleteByPermitId(String infoId);

    ResponseBo getListByPermitId(String permitId);
}
