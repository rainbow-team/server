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
import com.rainbow.security.dao.FacSecurityMapper;
import com.rainbow.security.domain.FacSecurity;
import com.rainbow.security.domain.extend.FacSecurityExtend;
import com.rainbow.security.service.FacSecurityService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;

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
@Service("facsecurityservice")
public class FacSecurityServiceImpl extends BaseService<FacSecurity> implements FacSecurityService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CacheManager cacheManager;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    FacMapper facMapper;

    @Autowired
    FacSecurityMapper facSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFacSecurity(FacSecurity facSecurity) {
        facSecurity.setId(GuidHelper.getGuid());
        facSecurity.setCreateDate(new Date());
        facSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(facSecurity.getAttachmentList(), facSecurity.getId());
        return facSecurityMapper.insert(facSecurity);
    }

    @Override
    public int modifyFacSecurity(FacSecurity facSecurity) {
        facSecurity.setModifyDate(new Date());
        return facSecurityMapper.updateByPrimaryKey(facSecurity);
    }

    @Override
    public ResponseBo getFacSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacSecurityExtend> list = facSecurityMapper.getFacSecurityList(map);

        PageInfo<FacSecurityExtend> pageInfo = new PageInfo<FacSecurityExtend>(list);

        PagingEntity<FacSecurityExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacSecurityById(String id) {
        FacSecurity result = facSecurityMapper.getFacSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportFacSecurity(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<FacSecurityExtend> list = facSecurityMapper.getFacSecurityList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (FacSecurityExtend facSecurityExtend : list) {
                String[] strs = new String[] { facSecurityExtend.getServiceDepartName(), facSecurityExtend.getFacName(),
                        facSecurityExtend.getFacStatusTypeValue(), facSecurityExtend.getCheckTypeValue(),
                        facSecurityExtend.getContent(), DateUtils.DateToString(facSecurityExtend.getFindDate()),
                        facSecurityExtend.getQuestionTypeValue(), facSecurityExtend.getQuestionNatureValue(),
                        facSecurityExtend.getReformStatusTypeValue(), facSecurityExtend.getSuperviseRequire(),
                        facSecurityExtend.getReformPlan(),
                        DateUtils.DateToString(facSecurityExtend.getReformCompleteDate()) };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "营运单位", "设施名称", "设施状态", "检查类型", "问题内容", "发现时间", "问题类别", "问题性质", "整改状态",
                "监督要求", "整改方案", "整改完成时间" };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核设施安全问题列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("核设施安全问题列表", "utf-8") + ".xls");
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
                List<FacSecurityExtend> list = ExcelHelper.convertToList(FacSecurityExtend.class, fileName, inputStream,
                        1, 12, 0);
                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    FacSecurityExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());
                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                        msg += "第" + (i + 2) + "行核设施营运单位和铀矿冶单位为空,";
                    } else {
                        String serviceDepartId = serviceDepartMapper
                                .getServiceDepartIdByName(item.getServiceDepartName());
                        if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                            msg += "第" + (i + 2) + "行核设施营运单位名称在数据库不存在，";
                        } else {
                            item.setServiceId(serviceDepartId);
                        }
                    }

                    if (!StrUtil.isNullOrEmpty(item.getFacName())) {
                        String facId = facMapper.getFacIdByName(item.getFacName());
                        if (StrUtil.isNullOrEmpty(facId)) {
                            msg += "第" + (i + 2) + "行核设施信息在数据库不存在，";
                        } else {
                            item.setFacId(facId);
                        }
                    }

                    if (!StrUtil.isNullOrEmpty(item.getFacStatusTypeValue())) {
                        mapConfig.put("tablename", "config_fac_status");
                        mapConfig.put("value", item.getFacStatusTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行设施状态在数据库不存在，";
                        } else {
                            item.setFacStatusId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getCheckTypeValue())) {
                        msg += "第" + (i + 2) + "行检查类型为空，";
                    } else {
                        mapConfig.put("tablename", "config_security_check_type");
                        mapConfig.put("value", item.getCheckTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行检查类型在数据库不存在，";
                        } else {
                            item.setCheckTypeId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getContent())) {
                        msg += "第" + (i + 2) + "行问题内容为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getQuestionTypeValue())) {
                        msg += "第" + (i + 2) + "行核设施安全问题类别为空，";
                    } else {
                        mapConfig.put("tablename", "config_fac_security_question_type");
                        mapConfig.put("value", item.getQuestionTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行核设施安全问题类别在数据库不存在，";
                        } else {
                            item.setQuestionTypeId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getQuestionNatureValue())) {
                        msg += "第" + (i + 2) + "行问题性质为空，";
                    } else {
                        mapConfig.put("tablename", "config_fac_security_question_nature");
                        mapConfig.put("value", item.getQuestionNatureValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行问题性质在数据库不存在，";
                        } else {
                            item.setQuestionNatureId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getReformStatusTypeValue())) {
                        msg += "第" + (i + 2) + "行整改状态为空，";
                    } else {
                        mapConfig.put("tablename", "config_security_reform_status");
                        mapConfig.put("value", item.getReformStatusTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行整改状态在数据库不存在，";
                        } else {
                            item.setReformStatusId(typeId);
                        }
                    }

                    // Excel数据重复判断
                    if (map.containsKey(item.getServiceId() + item.getFacId() + item.getFacStatusId()
                            + item.getContent() + item.getFindDate())) {
                        msg += "第" + (i + 2) + "行【营运单位】+【设施名称】+【设施状态】+【问题内容】+【发现时间】数据重复，";
                    } else {
                        map.put(item.getServiceId() + item.getFacId() + item.getFacStatusId() + item.getContent()
                                + item.getFindDate(), item.toString());
                    }

                    // 数据库重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("serviceId", item.getServiceId());
                    params.put("facId", item.getFacId());
                    params.put("facStatusId", item.getFacStatusId());
                    params.put("content", item.getContent());
                    params.put("findDate", item.getFindDate());

                    if (facSecurityMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "行【营运单位】+【设施名称】+【设施状态】+【问题内容】+【发现时间】与数据库中的数据存在重复，";
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

                    for (FacSecurityExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        facSecurityMapper.insert(data);

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