package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.*;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.dao.ServiceAnnualReportMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.domain.ServiceAnnualReport;
import com.rainbow.unit.domain.ServiceDepart;
import com.rainbow.unit.domain.ServiceDepartExtend;
import com.rainbow.unit.service.ServiceDepartService;
import net.sf.ehcache.CacheManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("servicedepartservice")
public class ServiceDepartServiceImpl extends BaseService<ServiceDepart> implements ServiceDepartService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    ServiceAnnualReportMapper annualReportMapper;

    @Autowired
    FacMapper facMapper;

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    ServiceAnnualReportMapper serviceAnnualReportMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addServiceDepart(ServiceDepart serviceDepart) {
        serviceDepart.setId(GuidHelper.getGuid());
        serviceDepart.setCreateDate(new Date());
        serviceDepart.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(serviceDepart.getAttachmentList(), serviceDepart.getId());
        return serviceDepartMapper.insert(serviceDepart);
    }

    @Override
    public int modifyServiceDepart(ServiceDepart serviceDepart) {
        serviceDepart.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(serviceDepart.getAttachmentList(), serviceDepart.getId());
        return serviceDepartMapper.updateByPrimaryKey(serviceDepart);
    }

    @Override
    public ResponseBo getServiceDepartList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ServiceDepartExtend> list = serviceDepartMapper.getServiceDepartList(map);

        PageInfo<ServiceDepartExtend> pageInfo = new PageInfo<ServiceDepartExtend>(list);

        PagingEntity<ServiceDepartExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getServiceDepartById(String id) {

        ServiceDepartExtend result = serviceDepartMapper.getServiceDepartByServiceId(id);

        if (result != null) {
            int num = facMapper.getFacNumByServiceId(id);

            result.setFacNum(num);
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败");
    }

    @Override
    public int deleteServiceDepartById(String id) {
        Object result = serviceDepartMapper.getServiceDepartRelationCount(id);
        if (result != null) {
            return serviceDepartMapper.deleteServiceDepartById(id);
        }
        return 0;
    }

    @Override
    public void exportServiceDepart(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<ServiceDepartExtend> list = serviceDepartMapper.getServiceDepartList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<ServiceAnnualReport> serviceAnnualReportList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (ServiceDepartExtend serviceDepartExtend : list) {
                String[] strs = new String[] { serviceDepartExtend.getName(), serviceDepartExtend.getGroupName(),
                        serviceDepartExtend.getSurvey(), serviceDepartExtend.getFeature(),
                        serviceDepartExtend.getCode(), serviceDepartExtend.getAddress(),
                        serviceDepartExtend.getEmergencyTel(), serviceDepartExtend.getFax(),
                        serviceDepartExtend.getOwner(), serviceDepartExtend.getLeader(),
                        serviceDepartExtend.getLeaderTel(), serviceDepartExtend.getDepartLeader(),
                        serviceDepartExtend.getDepartLeaderTel(), serviceDepartExtend.getNote() };
                cloumnValues.add(strs);

                // 年度报告信息
                Map<String, Object> map1 = new HashMap<>();
                map1.put("serviceId", serviceDepartExtend.getId());
                List<ServiceAnnualReport> list1 = serviceAnnualReportMapper.getServiceAnnualReportList(map1);
                if (list1 != null && list1.size() > 0) {
                    serviceAnnualReportList.addAll(list1);
                }
            }
        }

        String[] cloumnNames = new String[] { "单位名称", "所属集团", "基本概况", "厂址特征", "代号", "地址", "应急电话", "传真", "法人代表",
                "主管安全领导", "主管安全领导电话", "安全部门领导", "安全部门领导电话", "备注" };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核设施营运单位信息", cloumnNames, cloumnValues);

        // 年度报告信息
        String[] cloumnNames1 = new String[] { "单位名称", "报告年度" };

        cloumnValues = new ArrayList<>();
        if (serviceAnnualReportList.size() > 0) {
            for (ServiceAnnualReport serviceAnnualReport : serviceAnnualReportList) {

                int year = DateUtils.getDateYear(serviceAnnualReport.getReportYear());
                String[] strs = new String[] { serviceAnnualReport.getServiceName(), Integer.toString(year) };
                cloumnValues.add(strs);
            }
        }

        wb = ExportExcel.getHssfWorkBook(wb, "年度报告信息", cloumnNames1, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("核设施营运单位信息", "utf-8") + ".xls");
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
    public ResponseBo importServiceDepart(HttpServletRequest request) {

        Multipart part = new Multipart();
        // 获取前端传过来的file
        MultipartFile file = part.getUploadFile(request);
        FileInputStream inputStream = null;
        FileInputStream inputStream1 = null;

        String msg = "";

        try {
            if (file != null) {
                // 转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                inputStream1 = (FileInputStream) file.getInputStream();
                // 将导入的excel转化为实体
                List<ServiceDepartExtend> list = ExcelHelper.convertToList(ServiceDepartExtend.class, fileName,
                        inputStream, 1, 14, 0);
                List<ServiceAnnualReport> serviceAnnualReportList = ExcelHelper.convertToList(ServiceAnnualReport.class,
                        fileName, inputStream1, 1, 2, 1);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {

                    ServiceDepartExtend serviceDepartExtend = list.get(i);

                    serviceDepartExtend.setId(GuidHelper.getGuid());
                    if (StrUtil.isNullOrEmpty(serviceDepartExtend.getName())) {
                        msg += "第" + (i + 2) + "行单位名称为空，";
                    } else {

                        if (map.containsKey(serviceDepartExtend.getName())) {
                            msg += "第" + (i + 2) + "行单位名称重复，";
                        } else {
                            map.put(serviceDepartExtend.getName(), serviceDepartExtend.getId());
                        }

                        // 校验数据库是否重复
                        int count = serviceDepartMapper.getServiceDepartByName(serviceDepartExtend.getName());
                        if (count > 0) {
                            msg += "第" + (i + 2) + "行单位名称在数据库已存在，";
                        }
                    }

                    if (StrUtil.isNullOrEmpty(serviceDepartExtend.getGroupName())) {
                        msg += "第" + (i + 2) + "行所属集团为空，";
                    } else {

                        String groupId = groupMapper.getGroupIdByName(serviceDepartExtend.getGroupName());

                        if (StrUtil.isNullOrEmpty(groupId)) {
                            msg += "第" + (i + 2) + "行所属集团在数据库中不存在，";
                        } else {
                            serviceDepartExtend.setGroupId(groupId);
                        }

                    }
                }

                for (int j = 0; j < serviceAnnualReportList.size(); j++) {

                    ServiceAnnualReport serviceAnnualReport = serviceAnnualReportList.get(j);
                    serviceAnnualReport.setReportId(GuidHelper.getGuid());

                    if (StrUtil.isNullOrEmpty(serviceAnnualReport.getServiceName())) {
                        msg += "年度报告信息第" + (j + 2) + "行单位名称为空，";
                    } else {
                        String serviceId = map.get(serviceAnnualReport.getServiceName());
                        if (StrUtil.isNullOrEmpty(serviceId)) {
                            msg += "年度报告信息第" + (j + 2) + "行单位名称在核设施营运单位信息中不存在，";
                        } else {
                            serviceAnnualReport.setServiceId(serviceId);
                        }
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

                    for (ServiceDepartExtend serviceDepartExtend : list) {

                        serviceDepartExtend.setIsImport(1);
                        serviceDepartExtend.setCreateDate(new Date());
                        serviceDepartExtend.setModifyDate(new Date());
                        serviceDepartExtend.setCreatorId(user.getId());
                        serviceDepartExtend.setModifyId(user.getId());

                        serviceDepartMapper.insert(serviceDepartExtend);
                    }

                    for (ServiceAnnualReport serviceAnnualReport : serviceAnnualReportList) {
                        annualReportMapper.insert(serviceAnnualReport);
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
