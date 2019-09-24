package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.ExcelHelper;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.common.util.Multipart;
import com.rainbow.common.util.StrUtil;
import com.rainbow.common.util.UserUtils;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.permit.dao.EquipPermitMapper;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.domain.EquipPermitExtend;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.service.EquipPermitService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("equippermitservice")
public class EquipPermitServiceImpl extends BaseService<EquipPermit> implements EquipPermitService {

    @Autowired
    EquipPermitMapper equipPermitMapper;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheManager cacheManager;

    @Autowired
    EquipDepartMapper equipDepartMapper;

    @Autowired
    FacMapper facMapper;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Override
    public int addEquipPermit(EquipPermit equipPermit) {
        equipPermit.setId(GuidHelper.getGuid());
        equipPermit.setCreateDate(new Date());
        equipPermit.setModifyDate(new Date());
        return equipPermitMapper.insert(equipPermit);
    }

    @Override
    public int modifyEquipPermit(EquipPermit equipPermit) {
        equipPermit.setModifyDate(new Date());
        return equipPermitMapper.updateByPrimaryKey(equipPermit);
    }

    @Override
    public ResponseBo getEquipPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<EquipPermit> list = equipPermitMapper.getEquipPermitList(map);

        PageInfo<EquipPermit> pageInfo = new PageInfo<EquipPermit>(list);

        PagingEntity<EquipPermit> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEquipPermitById(String id) {
        EquipPermit result = equipPermitMapper.getEquipPermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public ResponseBo importData(HttpServletRequest request) {
        Multipart part = new Multipart();
        // ????????file
        MultipartFile file = part.getUploadFile(request);
        FileInputStream inputStream = null;
        // FileInputStream inputStream1 = null;

        String msg = "";
        try {
            if (file != null) {
                // ??????????
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                // inputStream1 = (FileInputStream) file.getInputStream();
                // ????excel?????
                List<EquipPermitExtend> list = ExcelHelper.convertToList(EquipPermitExtend.class, fileName, inputStream,
                        2, 12, 0);

                if (list.size() == 0) {
                    return ResponseBo.error("??????");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // ??
                for (int i = 0; i < list.size(); i++) {
                    EquipPermitExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());

                    if (StrUtil.isNullOrEmpty(item.getName())) {
                        msg += "?" + (i + 2) + "???????,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                        msg += "?" + (i + 2) + "????????????????,";
                    } else {
                        String serviceDepartId = serviceDepartMapper
                                .getServiceDepartIdByName(item.getServiceDepartName());
                        if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                            msg += "?" + (i + 2) + "??????????????????";
                        } else {
                            item.setServiceId(serviceDepartId);
                        }

                    }

                    if (StrUtil.isNullOrEmpty(item.getEquipDepartName())) {
                        msg += "?" + (i + 2) + "??????????????,";
                    } else {
                        String equipDepartId = equipDepartMapper.getEquipDepartIdByName(item.getEquipDepartName());
                        if (StrUtil.isNullOrEmpty(equipDepartId)) {
                            msg += "?" + (i + 2) + "??????????????????";
                        } else {
                            item.setEquipDepartId(equipDepartId);
                        }
                    }

                    if (!StrUtil.isNullOrEmpty(item.getFacName())) {
                        String facId = facMapper.getFacIdByName(item.getFacName());
                        if (StrUtil.isNullOrEmpty(facId)) {
                            msg += "?" + (i + 2) + "?????????????";
                        } else {
                            item.setFacId(facId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getTypeValue())) {
                        msg += "?" + (i + 2) + "????????";
                    } else {
                        mapConfig.put("tablename", "config_equip_type");
                        mapConfig.put("value", item.getTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "?" + (i + 2) + "?????????????";
                        } else {
                            item.setTypeId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getLevelValue())) {
                        msg += "?" + (i + 2) + "?????????";
                    } else {
                        mapConfig.put("tablename", "config_equip_level");
                        mapConfig.put("value", item.getLevelValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "?" + (i + 2) + "??????????????";
                        } else {
                            item.setLevelId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getStageValue())) {
                        msg += "?" + (i + 2) + "????????";
                    } else {
                        mapConfig.put("tablename", "config_equip_permit_stage");
                        mapConfig.put("value", item.getStageValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "?" + (i + 2) + "?????????????";
                        } else {
                            item.setStageId(typeId);
                        }
                    }

                    if (item.getPermitDate() == null) {
                        msg += "?" + (i + 2) + "???????,";
                    }

                    if (item.getValidateTime() == null) {
                        msg += "?" + (i + 2) + "???????,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getLicence())) {
                        msg += "?" + (i + 2) + "????????";
                    }

                    // Excel??????
                    if (map.containsKey(
                            item.getName() + item.getEquipDepartId() + item.getStageId() + item.getPermitDate())) {
                        msg += "?" + (i + 2) + "???????+???????+??????+???????????";
                    } else {
                        map.put(item.getName() + item.getEquipDepartId() + item.getStageId() + item.getPermitDate(),
                                item.toString());
                    }

                    // ???????
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("name", item.getName());
                    params.put("equipDepartId", item.getEquipDepartId());
                    params.put("stageId", item.getStageId());
                    params.put("permitDate", item.getPermitDate());

                    if (equipPermitMapper.verifyDuplication(params) > 0) {
                        msg += "?" + (i + 2) + "???????+???????+??????+???????????????????";
                    }

                }

                if (!msg.isEmpty()) {
                    return ResponseBo.error(msg);
                } else {
                    // ?????
                    SystemUser user = UserUtils.getCurrentUser(cacheManager);
                    if (user == null) {
                        user = new SystemUser();
                    }

                    for (EquipPermitExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        equipPermitMapper.insert(data);

                    }
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseBo.error(msg);
        }

        return ResponseBo.ok();
    }

}