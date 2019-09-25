package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.Umine;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UmineService extends IService<Umine> {

    int addUmine(Umine umine);

    int modifyUmine(Umine umine);

    int deleteUmineById(String id);

    ResponseBo getUmineList(Page page);

    ResponseBo getUmineById(String id);

    void exportUmine(Page page,HttpServletResponse response);
}
