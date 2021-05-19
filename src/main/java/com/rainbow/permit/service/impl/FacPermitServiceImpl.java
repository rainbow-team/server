package com.rainbow.permit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.cache.EHCacheUtils;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.*;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.monitor.domain.extend.ReportMonitorExtend;
import com.rainbow.permit.dao.FacPermitMapper;
import com.rainbow.permit.dao.PermitPublishScopeMapper;
import com.rainbow.permit.domain.FacPermit;
import com.rainbow.permit.domain.FacPermitExtend;
import com.rainbow.permit.domain.PermitPublishScope;
import com.rainbow.permit.service.FacPermitService;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.FacReportMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.service.FacService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    PermitPublishScopeMapper scopeMappers;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFacPermit(FacPermit facPermit) {
        String permitId = GuidHelper.getGuid();
        facPermit.setId(permitId);
        facPermit.setState("1");//用户新建数据默认“发布/待审”状态
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);
        facPermit.setCreatorId(user.getId());
        Date now = new Date();
        facPermit.setCreateDate(now);
        facPermit.setModifyDate(now);
        fileInfoService.updateFileInfoByIds(facPermit.getAttachmentList(), facPermit.getId());

        List<PermitPublishScope> list = facPermit.getPublishScopes().stream().map(item -> {
            PermitPublishScope pps = new PermitPublishScope();
            pps.setId(GuidHelper.getGuid());
            pps.setPermitId(permitId);
            pps.setUserId(item);
            return pps;
        }).collect(Collectors.toList());
        scopeMappers.add(list);
        return facPermitMapper.insert(facPermit);
    }

    @Override
    public int modifyFacPermit(FacPermit facPermit) {
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);
        facPermit.setModifyId(user.getId());
        facPermit.setModifyDate(new Date());

        //删除知悉范围后再新增
        scopeMappers.deleteByPermitId(facPermit.getId());
        List<PermitPublishScope> list = facPermit.getPublishScopes().stream().map(item -> {
            PermitPublishScope pps = new PermitPublishScope();
            pps.setId(GuidHelper.getGuid());
            pps.setPermitId(facPermit.getId());
            pps.setUserId(item);
            return pps;
        }).collect(Collectors.toList());
        scopeMappers.add(list);

        return facPermitMapper.updateByPrimaryKey(facPermit);
    }

    @Override
    public ResponseBo getFacPermitList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);
        map.put("userId", user.getId());
        List<FacPermitExtend> list = facPermitMapper.getFacPermitList(map);

        PageInfo<FacPermitExtend> pageInfo = new PageInfo<FacPermitExtend>(list);

        PagingEntity<FacPermitExtend> result = new PagingEntity<>(pageInfo);

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
    public void exportFacPermit(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);
        map.put("userId", user.getId());
        List<FacPermitExtend> list = facPermitMapper.getFacPermitList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (FacPermitExtend facPermitExtend : list) {
                String[] strs = new String[]{facPermitExtend.getServiceDepartName(), facPermitExtend.getFacName(),
                        facPermitExtend.getPermitStageValue(), facPermitExtend.getScope(),
                        DateUtils.DateToString(facPermitExtend.getPermitDate()), facPermitExtend.getLicence(),
                        facPermitExtend.getCondition(), facPermitExtend.getPromise(),
                        facPermitExtend.getSecurityLevelValue(), facPermitExtend.getSecurityGist(),
                        String.valueOf(facPermitExtend.getSecurityTimeLimit())};
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{"营运单位", "设施名称", "许可阶段", "许可范围", "许可时间", "许可文号", "许可条件",
                "审评承诺", "保密等级", "定密依据", "保密期限"};

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核设施许可信息列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("核设施许可信息列表", "utf-8") + ".xls");
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

        String msg = "";
        try {
            if (file != null) {
                // 转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                // 将导入的excel转化为实体
                List<FacPermitExtend> list = ExcelHelper.convertToList(FacPermitExtend.class, fileName, inputStream, 1,
                        11, 0);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验及字典转换
                for (int i = 0; i < list.size(); i++) {
                    FacPermitExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());

                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                        msg += "第" + (i + 2) + "行营运单位为空,";
                    } else {
                        String serviceDepartId = serviceDepartMapper
                                .getServiceDepartIdByName(item.getServiceDepartName());
                        if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                            msg += "第" + (i + 2) + "行营运单位在数据库不存在,";
                        } else {
                            item.setServiceId(serviceDepartId);
                        }
                    }

                    if (!StrUtil.isNullOrEmpty(item.getFacName())) {
                        String facId = facMapper.getFacIdByName(item.getFacName());
                        if (StrUtil.isNullOrEmpty(facId)) {
                            msg += "第" + (i + 2) + "行核设施信息在数据库中不存在,";
                        } else {
                            item.setFacId(facId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getPermitStageValue())) {
                        msg += "第" + (i + 2) + "行许可阶段为空,";
                    } else {
                        mapConfig.put("tablename", "config_fac_permit_stage");
                        mapConfig.put("value", item.getPermitStageValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行许可阶段在数据库中不存在,";
                        } else {
                            item.setStageId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getSecurityLevelValue())) {
                        msg += "第" + (i + 2) + "行保密等级为空,";
                    } else {
                        mapConfig.put("tablename", "config_info_security_level");
                        mapConfig.put("value", item.getSecurityLevelValue());
                        String level = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(level)) {
                            msg += "第" + (i + 2) + "行保密等级在数据库中不存在,";
                        } else {
                            item.setSecurityLevel(level);
                        }
                    }

                    if (item.getPermitDate() == null) {
                        msg += "第" + (i + 2) + "行许可时间为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getLicence())) {
                        msg += "第" + (i + 2) + "行许可文号为空,";
                    }

                    // Excel数据重复判断
                    if (map.containsKey(
                            item.getServiceId() + item.getFacId() + item.getStageId() + item.getPermitDate())) {
                        msg += "第" + (i + 2) + "行【单位名称】+【核设施名称】+【许可阶段】+【许可时间】在数据库中重复,";
                    } else {
                        map.put(item.getServiceId() + item.getFacId() + item.getStageId() + item.getPermitDate(),
                                item.toString());
                    }

                    // 数据库重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("serviceId", item.getServiceId());
                    params.put("facId", item.getFacId());
                    params.put("stageId", item.getStageId());
                    params.put("permitDate", item.getPermitDate());

                    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    // params.put("permitDate", sdf.format(item.getPermitDate()));

                    if (facPermitMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "行【单位名称】+【核设施名称】+【许可阶段】+【许可时间】在数据库中重复,";
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
                    for (FacPermitExtend data : list) {
                        data.setIsImport(1);
                        data.setState("0");//用户新建数据默认“发布/待审”状态
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
            return ResponseBo.error("数据导入失败!\r\t" + msg);
        }

        return ResponseBo.ok();
    }

    @Override
    public int audit(String id, String state) {
        FacPermit permit = new FacPermit();
        permit.setId(id);
        permit.setState(state);
        return this.updateNotNull(permit);
    }

}