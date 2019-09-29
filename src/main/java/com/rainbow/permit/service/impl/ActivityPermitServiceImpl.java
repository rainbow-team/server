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
import com.rainbow.monitor.domain.extend.ReportMonitorExtend;
import com.rainbow.permit.dao.ActivityPermitMapper;
import com.rainbow.permit.dao.EquipPermitMapper;
import com.rainbow.permit.domain.ActivityPermit;
import com.rainbow.permit.domain.ActivityPermitExtend;
import com.rainbow.permit.domain.EquipPermit;
import com.rainbow.permit.service.ActivityPermitService;
import com.rainbow.permit.service.EquipPermitService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.service.EquipDepartService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("activitypermitservice")
public class ActivityPermitServiceImpl extends BaseService<ActivityPermit> implements ActivityPermitService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ActivityPermitMapper activityPermitMapper;
    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    EquipDepartMapper equipDepartMapper;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    FacMapper facMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addActivityPermit(ActivityPermit activityPermit) {
        activityPermit.setId(GuidHelper.getGuid());
        activityPermit.setCreateDate(new Date());
        activityPermit.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(activityPermit.getAttachmentList(),activityPermit.getId());
        return activityPermitMapper.insert(activityPermit);
    }

    @Override
    public int modifyActivityPermit(ActivityPermit activityPermit) {
        activityPermit.setModifyDate(new Date());
        return activityPermitMapper.updateByPrimaryKey(activityPermit);
    }

    @Override
    public ResponseBo getActivityPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ActivityPermitExtend> list = activityPermitMapper.getActivityPermitList(map);

        PageInfo<ActivityPermitExtend> pageInfo = new PageInfo<ActivityPermitExtend>(list);

        PagingEntity<ActivityPermitExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getActivityPermitById(String id) {
        ActivityPermit result = activityPermitMapper.getActivityPermitById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportActivityPermit(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<ActivityPermitExtend> list = activityPermitMapper.getActivityPermitList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (ActivityPermitExtend activityPermitExtend : list) {
                StringBuffer buf=new StringBuffer();
                buf.append(activityPermitExtend.getServiceDepartName() == null ? "" : activityPermitExtend.getServiceDepartName())
                        .append(activityPermitExtend.getEquipDepartName() == null ? "" : activityPermitExtend.getEquipDepartName());

                String[] strs = new String[] {
                        buf.toString(),
                        activityPermitExtend.getFacName(),
                        activityPermitExtend.getName(), activityPermitExtend.getContent(),
                        activityPermitExtend.getTypeValue(),
                        DateUtils.DateToString(activityPermitExtend.getPermitDate()),
                        DateUtils.DateToString(activityPermitExtend.getValidateTime()),
                        activityPermitExtend.getLicence(),
                        activityPermitExtend.getpermitCondition(),
                        activityPermitExtend.getPromise()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "单位名称", "设施名称", "许可名称", "许可内容",
                "活动类型","许可时间", "有效期限", "许可文号", "许可条件", "审评承诺"};

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核活动许可信息列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("核活动许可信息列表", "utf-8") + ".xls");
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
                List<ActivityPermitExtend> list = ExcelHelper.convertToList(ActivityPermitExtend.class, fileName,
                        inputStream, 2, 11, 0);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // ??
                for (int i = 0; i < list.size(); i++) {
                    ActivityPermitExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());

                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName())
                            && StrUtil.isNullOrEmpty(item.getEquipDepartName())) {
                        msg += "第" + (i + 2) + "行营运单位为空,";
                    } else {
                        if (!StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                            String serviceDepartId = serviceDepartMapper
                                    .getServiceDepartIdByName(item.getServiceDepartName());
                            if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                                msg += "第" + (i + 2) + "行营运单位在数据库中不存在，";
                            } else {
                                item.setServiceId(serviceDepartId);
                            }
                        } else {
                            String equipDepartId = equipDepartMapper.getEquipDepartIdByName(item.getEquipDepartName());
                            if (StrUtil.isNullOrEmpty(equipDepartId)) {
                                msg += "第" + (i + 2) + "行核设备单位信息在数据库中不存在，";
                            } else {
                                item.setEquipDepartId(equipDepartId);
                            }
                        }

                    }

                    if (!StrUtil.isNullOrEmpty(item.getFacName())) {
                        String facId = facMapper.getFacIdByName(item.getFacName());
                        if (StrUtil.isNullOrEmpty(facId)) {
                            msg += "第" + (i + 2) + "行核设施信息在数据库中不存在，";
                        } else {
                            item.setFacId(facId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getName())) {
                        msg += "第" + (i + 2) + "行许可名称为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getContent())) {
                        msg += "第" + (i + 2) + "行许可内容为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getTypeValue())) {
                        msg += "第" + (i + 2) + "行活动类型为空，";
                    } else {
                        mapConfig.put("tablename", "config_activity_type");
                        mapConfig.put("value", item.getTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行活动类型在数据库不存在，";
                        } else {
                            item.setActivityTypeId(typeId);
                        }
                    }

                    if (item.getPermitDate() == null) {
                        msg += "第" + (i + 2) + "许可时间为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getLicence())) {
                        msg += "第" + (i + 2) + "许可文号为空,";
                    }

                    // Excel??????
                    if (map.containsKey(
                            item.getServiceId() + item.getFacId() + item.getActivityTypeId() + item.getPermitDate())) {
                        msg += "第" + (i + 2) + "行【单位名称】+【设施名称】+【活动类型】+【许可时间】数据重复";
                    } else {
                        map.put(item.getServiceId() + item.getFacId() + item.getActivityTypeId() + item.getPermitDate(),
                                item.toString());
                    }

                    // ???????
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("serviceId", item.getServiceId());
                    params.put("facId", item.getFacId());
                    params.put("activityTypeId", item.getActivityTypeId());
                    params.put("permitDate", item.getPermitDate());

                    if (activityPermitMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "行【单位名称】+【设施名称】+【活动类型】+【许可时间】在数据库中重复";
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

                    for (ActivityPermitExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        activityPermitMapper.insert(data);

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