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
import com.rainbow.security.dao.UminePlaceSecurityMapper;
import com.rainbow.security.domain.UminePlaceSecurity;
import com.rainbow.security.domain.extend.UminePlaceSecurityExtend;
import com.rainbow.security.service.UminePlaceSecurityService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;
import com.rainbow.unit.dao.UmineplaceMapper;

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
@Service("umineplacesecurityservice")
public class UminePlaceSecurityServiceImpl extends BaseService<UminePlaceSecurity>
        implements UminePlaceSecurityService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CacheManager cacheManager;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    UmineMapper umineMapper;

    @Autowired
    UmineplaceMapper umineplaceMapper;

    @Autowired
    UminePlaceSecurityMapper uminePlaceSecurityMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addUminePlaceSecurity(UminePlaceSecurity uminePlaceSecurity) {
        uminePlaceSecurity.setId(GuidHelper.getGuid());
        uminePlaceSecurity.setCreateDate(new Date());
        uminePlaceSecurity.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(uminePlaceSecurity.getAttachmentList(), uminePlaceSecurity.getId());
        return uminePlaceSecurityMapper.insert(uminePlaceSecurity);
    }

    @Override
    public int modifyUminePlaceSecurity(UminePlaceSecurity uminePlaceSecurity) {
        uminePlaceSecurity.setModifyDate(new Date());
        return uminePlaceSecurityMapper.updateByPrimaryKey(uminePlaceSecurity);
    }

    @Override
    public ResponseBo getUminePlaceSecurityList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceSecurityExtend> list = uminePlaceSecurityMapper.getUminePlaceSecurityList(map);

        PageInfo<UminePlaceSecurityExtend> pageInfo = new PageInfo<UminePlaceSecurityExtend>(list);

        PagingEntity<UminePlaceSecurityExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUminePlaceSecurityById(String id) {
        UminePlaceSecurity result = uminePlaceSecurityMapper.getUminePlaceSecurityById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportUmineplaceSecurity(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<UminePlaceSecurityExtend> list = uminePlaceSecurityMapper.getUminePlaceSecurityList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UminePlaceSecurityExtend uminePlaceSecurityExtend : list) {
                String[] strs = new String[] { uminePlaceSecurityExtend.getUmineName(),
                        uminePlaceSecurityExtend.getUminePlaceName(),
                        uminePlaceSecurityExtend.getUminePlaceStatusTypeValue(),
                        uminePlaceSecurityExtend.getCheckTypeValue(), uminePlaceSecurityExtend.getContent(),
                        DateUtils.DateToString(uminePlaceSecurityExtend.getFindDate()),
                        uminePlaceSecurityExtend.getQuestionTypeValue(),
                        uminePlaceSecurityExtend.getQuestionNatureValue(),
                        uminePlaceSecurityExtend.getReformStatusTypeValue(),
                        uminePlaceSecurityExtend.getSuperviseRequire(), uminePlaceSecurityExtend.getReformPlan(),
                        DateUtils.DateToString(uminePlaceSecurityExtend.getReformCompleteDate()) };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "营运单位", "设施名称", "设施状态", "发现方式", "问题内容", "发现时间", "问题类别", "问题性质", "整改状态",
                "监管要求", "整改方案", "整改完成时间", };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "铀尾矿(渣)库安全问题", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("铀尾矿(渣)库安全问题", "utf-8") + ".xls");
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
                List<UminePlaceSecurityExtend> list = ExcelHelper.convertToList(UminePlaceSecurityExtend.class,
                        fileName, inputStream, 1, 12, 0);
                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    UminePlaceSecurityExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());
                    if (StrUtil.isNullOrEmpty(item.getUmineName())) {
                        msg += "第" + (i + 2) + "行铀矿冶单位为空,";
                    } else {
                        String umineId = umineMapper.getUmineIdByName(item.getUmineName());
                        if (StrUtil.isNullOrEmpty(umineId)) {
                            msg += "第" + (i + 2) + "行铀矿冶单位在数据库不存在，";
                        } else {
                            item.setUmineId(umineId);
                        }
                    }

                    if (!StrUtil.isNullOrEmpty(item.getUminePlaceName())) {
                        String uminePlaceId = umineplaceMapper.getUminePlaceIdByName(item.getUminePlaceName());
                        if (StrUtil.isNullOrEmpty(uminePlaceId)) {
                            msg += "第" + (i + 2) + "行铀尾矿(渣)库在数据库不存在，";
                        } else {
                            item.setUminePlaceId(uminePlaceId);
                        }
                    }

                    if (!StrUtil.isNullOrEmpty(item.getUminePlaceStatusTypeValue())) {
                        mapConfig.put("tablename", "config_umine_place_status");
                        mapConfig.put("value", item.getUminePlaceStatusTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行设施状态在数据库不存在，";
                        } else {
                            item.setStatusId(typeId);
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
                        mapConfig.put("tablename", "config_umine_place_security_question_type");
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
                        mapConfig.put("tablename", "config_umine_place_security_question_nature");
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
                    if (map.containsKey(item.getUmineId() + item.getUminePlaceId() + item.getStatusId()
                            + item.getContent() + item.getFindDate())) {
                        msg += "第" + (i + 2) + "行【营运单位】+【设施名称】+【设施状态】+【问题内容】+【发现时间】数据重复，";
                    } else {
                        map.put(item.getUmineId() + item.getUminePlaceId() + item.getStatusId() + item.getContent()
                                + item.getFindDate(), item.toString());
                    }

                    // 数据库重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("umineId", item.getUmineId());
                    params.put("uminePlaceId", item.getUminePlaceId());
                    params.put("statusId", item.getStatusId());
                    params.put("content", item.getContent());
                    params.put("findDate", item.getFindDate());

                    if (uminePlaceSecurityMapper.verifyDuplication(params) > 0) {
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

                    for (UminePlaceSecurityExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        uminePlaceSecurityMapper.insert(data);

                    }
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseBo.error("数据导入失败!\r\t" + msg);
        }

        return ResponseBo.ok();
    }
}