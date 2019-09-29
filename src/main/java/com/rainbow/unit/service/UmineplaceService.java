package com.rainbow.unit.service;

import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.IService;
import com.rainbow.unit.domain.EquipDepart;
import com.rainbow.unit.domain.Umineplace;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/6/27 17:08
 * @Description:
 **/
public interface UmineplaceService extends IService<Umineplace> {

    int addUmineplace(Umineplace umineplace);

    int modifyUmineplace(Umineplace umineplace);

    int deleteUmineplaceById(String id);

    ResponseBo getUmineplaceList(Page page);

    ResponseBo getUmineplaceById(String id);

    ResponseBo getUmineplaceListByUmineId(String umineId);

    ResponseBo importData(HttpServletRequest request);

    void exportUminePlace(Page page, HttpServletResponse response);
}
