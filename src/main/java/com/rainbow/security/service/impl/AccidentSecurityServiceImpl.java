package com.rainbow.security.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExcelHelper;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.common.util.Multipart;
import com.rainbow.common.util.StrUtil;
import com.rainbow.common.util.UserUtils;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.security.dao.AccidentSecurityMapper;
import com.rainbow.security.domain.AccidentSecurity;
import com.rainbow.security.domain.extend.AccidentSecurityExtend;
import com.rainbow.security.service.AccidentSecurityService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("accidentsecurityservice")
public class AccidentSecurityServiceImpl extends BaseService<AccidentSecurity> implements AccidentSecurityService {

    @Autowired
    AccidentSecurityMapper accidentSecurityMapper;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CacheManager cacheManager;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    UmineMapper umineMapper;

    @Override
    public int addAccidentSecurity(AccidentSecurity accidentSecurity) {
        accidentSecurity.setId(GuidHelper.getGuid());
        accidentSecurity.setCreateDate(new Date());
        accidentSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(accidentSecurity.getAttachmentList(), accidentSecurity.getId());
        return accidentSecurityMapper.insert(accidentSecurity);
    }

    @Override
    public int modifyAccidentSecurity(AccidentSecurity accidentSecurity) {
        accidentSecurity.setModifyDate(new Date());
        return accidentSecurityMapper.updateByPrimaryKey(accidentSecurity);
    }

    @Override
    public ResponseBo getAccidentSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<AccidentSecurityExtend> list = accidentSecurityMapper.getAccidentSecurityList(map);

        PageInfo<AccidentSecurityExtend> pageInfo = new PageInfo<AccidentSecurityExtend>(list);

        PagingEntity<AccidentSecurityExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getAccidentSecurityById(String id) {
        AccidentSecurity result = accidentSecurityMapper.getAccidentSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportAccidentSecurity(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<AccidentSecurityExtend> list = accidentSecurityMapper.getAccidentSecurityList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (AccidentSecurityExtend accidentSecurityExtend : list) {
                String departName = "";
                String name = "";
                String status = "";

                if (accidentSecurityExtend.getServiceDepartName() != null) {
                    departName += accidentSecurityExtend.getServiceDepartName();
                }
                if (accidentSecurityExtend.getUmineName() != null) {
                    departName += accidentSecurityExtend.getUmineName();
                }

                if (accidentSecurityExtend.getFacName() != null) {
                    name += accidentSecurityExtend.getFacName();
                }
                if (accidentSecurityExtend.getUminePlaceName() != null) {
                    name += accidentSecurityExtend.getUminePlaceName();
                }

                if (accidentSecurityExtend.getFacStatusValue() != null) {
                    status += accidentSecurityExtend.getFacStatusValue();
                }
                if (accidentSecurityExtend.getUminePlaceStatusValue() != null) {
                    status += accidentSecurityExtend.getUminePlaceStatusValue();
                }

                String[] strs = new String[] { departName, name, status, accidentSecurityExtend.getName(),
                        DateUtils.DateToString(accidentSecurityExtend.getOccurDate()),
                        accidentSecurityExtend.getProcess(), accidentSecurityExtend.getConsequence(),
                        accidentSecurityExtend.getReason(), accidentSecurityExtend.getTypeValue(),
                        accidentSecurityExtend.getNatureValue(), accidentSecurityExtend.getMeasure(),
                        accidentSecurityExtend.getFeedback() };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "营运单位", "设施名称", "设施状态", "事故事件名称", "事故事件发生时间", "事故事件过程", "事故事件后果", "原因分析",
                "事故事件类别", "事故事件性质", "处理措施", "经验反馈", };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "事故事件列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("事故事件列表", "utf-8") + ".xls");
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
        // 获取前端传过来的file
        MultipartFile file = part.getUploadFile(request);
        FileInputStream inputStream = null;
        // FileInputStream inputStream1 = null;

        String msg = "";
        try {
            if (file != null) {
                // 转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                // inputStream1 = (FileInputStream) file.getInputStream();

                // 将导入的excel转化为实体
                List<AccidentSecurityExtend> list = ExcelHelper.convertToList(AccidentSecurityExtend.class, fileName,
                        inputStream, 1, 15, 0);
                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    AccidentSecurityExtend item = list.get(i);

                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName())
                            && StrUtil.isNullOrEmpty(item.getUmineName())) {
                        msg += "第" + (i + 2) + "行核设施营运单位和铀矿冶单位为空,";
                    } else {
                        if (!StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                            String serviceDepartId = serviceDepartMapper
                                    .getServiceDepartIdByName(item.getServiceDepartName());
                            if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                                msg += "第" + (i + 2) + "行核设施营运单位名称在数据库不存在，";
                            } else {
                                item.setServiceId(serviceDepartId);
                            }
                        }
                        if (!StrUtil.isNullOrEmpty(item.getUmineName())) {
                            String umineId = umineMapper.getUmineIdByName(item.getUmineName());
                            if (StrUtil.isNullOrEmpty(umineId)) {
                                msg += "第" + (i + 2) + "行铀矿冶单位在数据库不存在，";
                            } else {
                                item.setUmineId(umineId);
                            }
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getFacStatusValue())) {
                        msg += "第" + (i + 2) + "行设施状态为空，";
                    } else {
                        mapConfig.put("tablename", "config_fac_status");
                        mapConfig.put("value", item.getFacStatusValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行文件类型名称在数据库不存在，";
                        } else {
                            item.setFacStatusId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getUminePlaceStatusValue())) {
                        msg += "第" + (i + 2) + "行铀尾矿(渣)库状态为空，";
                    } else {
                        mapConfig.put("tablename", "config_umine_place_status");
                        mapConfig.put("value", item.getUminePlaceStatusValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行铀尾矿(渣)库状态在数据库不存在，";
                        } else {
                            item.setUminePlaceStatusId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getName())) {
                        msg += "第" + (i + 2) + "行事件名称为空,";
                    }

                    if (item.getOccurDate() == null) {
                        msg += "第" + (i + 2) + "行发生时间为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getProcess())) {
                        msg += "第" + (i + 2) + "行事件过程为空,";
                    }

                    // Excel数据重复判断
                    if (map.containsKey(item.getServiceId() + item.getUmineId() + item.getFacId()
                            + item.getUminePlaceId() + item.getFacStatusId() + item.getUminePlaceStatusId()
                            + item.getName() + item.getOccurDate().toString())) {
                        msg += "第" + (i + 2) + "行【单位名称】+【设施名称】+【设施状态】+【事故事件名称】+【事故事件发生时间】数据重复，";
                    } else {
                        map.put(item.getServiceId() + item.getUmineId() + item.getFacId() + item.getUminePlaceId()
                                + item.getFacStatusId() + item.getUminePlaceStatusId() + item.getName()
                                + item.getOccurDate().toString(), item.toString());
                    }

                    // 数据库重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("umineId", item.getUmineId());
                    params.put("serviceId", item.getServiceId());
                    params.put("facId", item.getFacId());
                    params.put("uminePlaceId", item.getUminePlaceId());
                    params.put("facStatusId", item.getFacStatusId());
                    params.put("uminePlaceStatusId", item.getUminePlaceStatusId());
                    params.put("name", item.getName());
                    params.put("occurDate", item.getOccurDate());

                    if (accidentSecurityMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "行【单位名称】+【设施名称】+【设施状态】+【事故事件名称】+【事故事件发生时间】与数据库中的数据存在重复，";
                    }

                }

                if (!msg.isEmpty()) {
                    return ResponseBo.error(msg);
                } else {
                    // 插入数据库
                    SystemUser user = UserUtils.getCurrentUser(cacheManager);
                    if (user == null) {
                        user = new SystemUser();
                    }

                    for (AccidentSecurityExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        accidentSecurityMapper.insert(data);

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