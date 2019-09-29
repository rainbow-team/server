package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.*;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.permit.dao.EquipPermitMapper;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.domain.EquipPermitExtend;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.domain.FacPermitExtend;
import com.rainbow.permit.service.EquipPermitService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addEquipPermit(EquipPermit equipPermit) {
        equipPermit.setId(GuidHelper.getGuid());
        equipPermit.setCreateDate(new Date());
        equipPermit.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(equipPermit.getAttachmentList(),equipPermit.getId());
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
        List<EquipPermitExtend> list = equipPermitMapper.getEquipPermitList(map);

        PageInfo<EquipPermitExtend> pageInfo = new PageInfo<EquipPermitExtend>(list);

        PagingEntity<EquipPermitExtend> result = new PagingEntity<>(pageInfo);

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
    public void exportEquipPermit(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<EquipPermitExtend> list = equipPermitMapper.getEquipPermitList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (EquipPermitExtend equipPermitExtend : list) {
                String[] strs = new String[] {
                        equipPermitExtend.getName(),
                        equipPermitExtend.getEquipDepartName(), equipPermitExtend.getServiceDepartName(),
                        equipPermitExtend.getFacName(),equipPermitExtend.getTypeValue(),equipPermitExtend.getLevelValue(),
                        equipPermitExtend.getStageValue(),
                        DateUtils.DateToString(equipPermitExtend.getPermitDate()),
                        DateUtils.DateToString(equipPermitExtend.getValidateTime()),
                        equipPermitExtend.getLicence(), equipPermitExtend.getCondition(),equipPermitExtend.getPromise()};
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "设备名称", "核设备单位", "核设施营运单位", "核设施名称",
                "设备类别", "核安全级别", "许可阶段","许可时间", "有效期限", "许可文号", "许可条件", "审评承诺"};

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核安全设备许可信息列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("核安全设备许可信息列表", "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();
        } catch (Exception e) {

        }
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
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // ??
                for (int i = 0; i < list.size(); i++) {
                    EquipPermitExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());

                    if (StrUtil.isNullOrEmpty(item.getName())) {
                        msg += "第" + (i + 2) + "行设备名称为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                        msg += "第" + (i + 2) + "行营运单位为空,";
                    } else {
                        String serviceDepartId = serviceDepartMapper
                                .getServiceDepartIdByName(item.getServiceDepartName());
                        if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                            msg += "第" + (i + 2) + "行营运单位在数据库中不存在,";
                        } else {
                            item.setServiceId(serviceDepartId);
                        }

                    }

                    if (StrUtil.isNullOrEmpty(item.getEquipDepartName())) {
                        msg += "第" + (i + 2) + "行核设备单位为空,";
                    } else {
                        String equipDepartId = equipDepartMapper.getEquipDepartIdByName(item.getEquipDepartName());
                        if (StrUtil.isNullOrEmpty(equipDepartId)) {
                            msg += "第" + (i + 2) + "行核设备单位在数据库中不存在,";
                        } else {
                            item.setEquipDepartId(equipDepartId);
                        }
                    }

                    if (!StrUtil.isNullOrEmpty(item.getFacName())) {
                        String facId = facMapper.getFacIdByName(item.getFacName());
                        if (StrUtil.isNullOrEmpty(facId)) {
                            msg += "第" + (i + 2) + "行核设施名称在数据库中不存在,";
                        } else {
                            item.setFacId(facId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getTypeValue())) {
                        msg += "第" + (i + 2) + "行设备类别为空,";
                    } else {
                        mapConfig.put("tablename", "config_equip_type");
                        mapConfig.put("value", item.getTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行设备类别在数据库中不存在,";
                        } else {
                            item.setTypeId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getLevelValue())) {
                        msg += "第" + (i + 2) + "行安全级别为空,";
                    } else {
                        mapConfig.put("tablename", "config_equip_level");
                        mapConfig.put("value", item.getLevelValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行安全级别在数据库中不存在,";
                        } else {
                            item.setLevelId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getStageValue())) {
                        msg += "第" + (i + 2) + "行许可阶段为空,";
                    } else {
                        mapConfig.put("tablename", "config_equip_permit_stage");
                        mapConfig.put("value", item.getStageValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行许可阶段在数据库中不存在,";
                        } else {
                            item.setStageId(typeId);
                        }
                    }

                    if (item.getPermitDate() == null) {
                        msg += "第" + (i + 2) + "行许可时间为空,";
                    }

                    if (item.getValidateTime() == null) {
                        msg += "第" + (i + 2) + "行有效期限为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getLicence())) {
                        msg += "第" + (i + 2) + "行许可文号为空,";
                    }

                    // Excel??????
                    if (map.containsKey(
                            item.getName() + item.getEquipDepartId() + item.getStageId() + item.getPermitDate())) {
                        msg += "?" + (i + 2) + "行【设备名称】+【核设备单位】+【许可阶段】+【许可时间】数据重复";
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
                        msg += "?" + (i + 2) + "行【设备名称】+【核设备单位】+【许可阶段】+【许可时间】在数据库中重复";
                        ;
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