package com.rainbow.monitor.service.impl;

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
import com.rainbow.monitor.dao.ReportMonitorMapper;
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.ReportMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.domain.extend.DailyMonitorExtend;
import com.rainbow.monitor.domain.extend.ReportMonitorExtend;
import com.rainbow.monitor.service.ReportMonitorService;
import com.rainbow.monitor.service.WitnessMonitorService;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.system.domain.SystemUser;

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
@Service("reportmonitorservice")
public class ReportMonitorServiceImpl extends BaseService<ReportMonitor> implements ReportMonitorService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    SystemConfigMapper systemConfigMapper;
    @Autowired
    ReportMonitorMapper reportMonitorMapper;
    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addReportMonitor(ReportMonitor reportMonitor) {
        reportMonitor.setId(GuidHelper.getGuid());
        reportMonitor.setCreateDate(new Date());
        reportMonitor.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(reportMonitor.getAttachmentList(), reportMonitor.getId());
        return reportMonitorMapper.insert(reportMonitor);
    }

    @Override
    public int modifyReportMonitor(ReportMonitor reportMonitor) {
        reportMonitor.setModifyDate(new Date());
        return reportMonitorMapper.updateByPrimaryKey(reportMonitor);
    }

    @Override
    public ResponseBo getReportMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ReportMonitorExtend> list = reportMonitorMapper.getReportMonitorList(map);

        PageInfo<ReportMonitorExtend> pageInfo = new PageInfo<ReportMonitorExtend>(list);

        PagingEntity<ReportMonitorExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getReportMonitorById(String id) {
        ReportMonitor result = reportMonitorMapper.getReportMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportReportMonitor(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<ReportMonitorExtend> list = reportMonitorMapper.getReportMonitorList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (ReportMonitorExtend reportMonitorExtend : list) {
                String[] strs = new String[] { reportMonitorExtend.getOrgName(), reportMonitorExtend.getName(),
                        reportMonitorExtend.getReportTypeValue(),
                        DateUtils.DateToString(reportMonitorExtend.getreportDate()) };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "监管机构", "报告名称", "报告类型", "报告时间" };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "监督报告信息列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("监督报告信息列表", "utf-8") + ".xls");
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
        FileInputStream inputStream1 = null;

        String msg = "";
        try {
            if (file != null) {
                // 转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                inputStream1 = (FileInputStream) file.getInputStream();
                // 将导入的excel转化为实体
                List<ReportMonitorExtend> list = ExcelHelper.convertToList(ReportMonitorExtend.class, fileName,
                        inputStream, 1, 4, 0);
                // List<SupervisionTrainRecordExtend> supervisionTrainRecordExtendList =
                // ExcelHelper.convertToList(SupervisionTrainRecordExtend.class, fileName,
                // inputStream1, 2, 6,1);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    ReportMonitorExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());

                    if (StrUtil.isNullOrEmpty(item.getOrgName())) {
                        msg += "第" + (i + 2) + "行核安全监管机构名称为空，";
                    } else {
                        String orgId = orgMapper.getOrgOrSastindIdByName(item.getOrgName());
                        if (StrUtil.isNullOrEmpty(orgId)) {
                            msg += "第" + (i + 2) + "行核安全监管机构名称在数据库不存在，";
                        } else {
                            item.setOrgId(orgId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getName())) {
                        msg += "第" + (i + 2) + "报告名称为空,";
                    }

                    if (item.getreportDate() == null) {
                        msg += "第" + (i + 2) + "行报告时间为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getReportTypeValue())) {
                        msg += "第" + (i + 2) + "行文件类型名称为空，";
                    } else {
                        mapConfig.put("tablename", "config_monitor_report_type");
                        mapConfig.put("value", item.getReportTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行文件类型名称在数据库不存在，";
                        } else {
                            item.setReportTypeId(typeId);
                        }
                    }

                    // Excel数据重复判断
                    if (map.containsKey(item.getOrgId() + item.getName() + item.getReportTypeId()
                            + item.getreportDate().toString())) {
                        msg += "第" + (i + 2) + "行【监管机构】+【报告名称】+【报告类型】+【报告时间】数据重复，";
                    } else {
                        map.put(item.getOrgId() + item.getName() + item.getReportTypeId()
                                + item.getreportDate().toString(), item.toString());
                    }
                    // 重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("orgId", item.getOrgId());
                    params.put("name", item.getName());
                    params.put("reportTypeId", item.getReportTypeId());
                    params.put("reportDate", item.getreportDate());

                    if (reportMonitorMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "行【监管机构】+【报告名称】+【报告类型】+【报告时间】与数据库中的数据存在重复，";
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

                    for (ReportMonitorExtend ReportMonitorExtend : list) {

                        ReportMonitorExtend.setIsImport(1);
                        ReportMonitorExtend.setCreateDate(new Date());
                        ReportMonitorExtend.setModifyDate(new Date());
                        ReportMonitorExtend.setCreatorId(user.getId());
                        ReportMonitorExtend.setModifyId(user.getId());

                        reportMonitorMapper.insert(ReportMonitorExtend);

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