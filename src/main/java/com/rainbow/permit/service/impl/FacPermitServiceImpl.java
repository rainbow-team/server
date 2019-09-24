package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
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
import com.rainbow.permit.dao.FacPermitMapper;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.domain.FacPermitExtend;
import com.rainbow.permit.service.FacPermitService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.FacReportMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.service.FacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("facpermitservice")
public class FacPermitServiceImpl extends BaseService<FacPermit> implements FacPermitService {

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

    @Autowired
    FacPermitMapper facPermitMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFacPermit(FacPermit facPermit) {
        facPermit.setId(GuidHelper.getGuid());
        facPermit.setCreateDate(new Date());
        facPermit.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(facPermit.getAttachmentList(), facPermit.getId());
        return facPermitMapper.insert(facPermit);
    }

    @Override
    public int modifyFacPermit(FacPermit facPermit) {
        facPermit.setModifyDate(new Date());
        return facPermitMapper.updateByPrimaryKey(facPermit);
    }

    @Override
    public ResponseBo getFacPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacPermit> list = facPermitMapper.getFacPermitList(map);

        PageInfo<FacPermit> pageInfo = new PageInfo<FacPermit>(list);

        PagingEntity<FacPermit> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacPermitById(String id) {
        FacPermit result = facPermitMapper.getFacPermitById(id);
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
                List<FacPermitExtend> list = ExcelHelper.convertToList(FacPermitExtend.class, fileName, inputStream, 2,
                        8, 0);

                if (list.size() == 0) {
                    return ResponseBo.error("??????");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // ??
                for (int i = 0; i < list.size(); i++) {
                    FacPermitExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());

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

                    if (!StrUtil.isNullOrEmpty(item.getFacName())) {
                        String facId = facMapper.getFacIdByName(item.getFacName());
                        if (StrUtil.isNullOrEmpty(facId)) {
                            msg += "?" + (i + 2) + "?????????????";
                        } else {
                            item.setFacId(facId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getPermitStageValue())) {
                        msg += "?" + (i + 2) + "????????";
                    } else {
                        mapConfig.put("tablename", "config_fac_permit_stage");
                        mapConfig.put("value", item.getPermitStageValue());
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

                    if (StrUtil.isNullOrEmpty(item.getLicence())) {
                        msg += "?" + (i + 2) + "????????";
                    }

                    // Excel??????
                    if (map.containsKey(
                            item.getServiceId() + item.getFacId() + item.getStageId() + item.getPermitDate())) {
                        msg += "?" + (i + 2) + "???????+???????+??????+???????????";
                    } else {
                        map.put(item.getServiceId() + item.getFacId() + item.getStageId() + item.getPermitDate(),
                                item.toString());
                    }

                    // ???????
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("serviceId", item.getServiceId());
                    params.put("facId", item.getFacId());
                    params.put("stageId", item.getStageId());
                    params.put("permitDate", item.getPermitDate());

                    if (facPermitMapper.verifyDuplication(params) > 0) {
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

                    for (FacPermitExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        facPermitMapper.insert(data);

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