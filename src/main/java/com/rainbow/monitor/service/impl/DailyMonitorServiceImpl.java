package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
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
import com.rainbow.monitor.dao.DailyMonitorMapper;
import com.rainbow.monitor.domain.DailyMonitor;
import com.rainbow.monitor.domain.extend.DailyMonitorExtend;
import com.rainbow.monitor.service.DailyMonitorService;
import com.rainbow.security.domain.extend.EquipSecurityExtend;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.ServiceDepartMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("dailymonitorservice")
public class DailyMonitorServiceImpl extends BaseService<DailyMonitor> implements DailyMonitorService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    DailyMonitorMapper dailyMonitorMapper;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    OrgMapper orgMapper;

    @Override
    public int addDailyMonitor(DailyMonitor activityCheck) {
        activityCheck.setId(GuidHelper.getGuid());
        activityCheck.setCreateDate(new Date());
        activityCheck.setModifyDate(new Date());
        return dailyMonitorMapper.insert(activityCheck);
    }

    @Override
    public int modifyDailyMonitor(DailyMonitor activityCheck) {
        activityCheck.setModifyDate(new Date());
        return dailyMonitorMapper.updateByPrimaryKey(activityCheck);
    }

    @Override
    public ResponseBo getDailyMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<DailyMonitorExtend> list = dailyMonitorMapper.getDailyMonitorList(map);

        PageInfo<DailyMonitorExtend> pageInfo = new PageInfo<DailyMonitorExtend>(list);

        PagingEntity<DailyMonitorExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getDailyMonitorById(String id) {
        DailyMonitor result = dailyMonitorMapper.getDailyMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportDailyMonitor(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<DailyMonitorExtend> list = dailyMonitorMapper.getDailyMonitorList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (DailyMonitorExtend dailyMonitorExtend : list) {
                String[] strs = new String[] { dailyMonitorExtend.getServiceDepartName(),
                        dailyMonitorExtend.getFacName(), dailyMonitorExtend.getStatusValue(),
                        dailyMonitorExtend.getOrgName(), dailyMonitorExtend.getFileTypeValue(),
                        dailyMonitorExtend.getFileName(), DateUtils.DateToString(dailyMonitorExtend.getFileDate()) };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "营运单位", "设施名称", "设施状态", "授权监管机构", "文件类型", "文件名称", "文件时间" };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "日常监督信息列表", cloumnNames, cloumnValues);

        try {
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("日常监督信息列表", "utf-8") + ".xls");
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
                List<DailyMonitorExtend> list = ExcelHelper.convertToList(DailyMonitorExtend.class, fileName,
                        inputStream, 2, 7, 0);
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
                    DailyMonitorExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());

                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                        msg += "第" + (i + 2) + "行营运单位为空,";
                    } else {
                        String serviceDepartId = serviceDepartMapper
                                .getServiceDepartIdByName(item.getServiceDepartName());
                        if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                            msg += "第" + (i + 2) + "行营运单位名称在数据库不存在，";
                        } else {
                            item.setServiceId(serviceDepartId);
                        }
                    }
                    if (StrUtil.isNullOrEmpty(item.getOrgName())) {
                        msg += "第" + (i + 2) + "行核安全授权监管机构名称为空，";
                    } else {
                        String orgId = orgMapper.getOrgIdByName(item.getOrgName());
                        if (StrUtil.isNullOrEmpty(orgId)) {
                            msg += "第" + (i + 2) + "行核安全授权监管机构名称在数据库不存在，";
                        } else {
                            item.setOrgId(orgId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getFileName())) {
                        msg += "第" + (i + 2) + "行文件名称为空,";
                    }

                    if (item.getFileDate() == null) {
                        msg += "第" + (i + 2) + "行文件时间为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getFileTypeValue())) {
                        msg += "第" + (i + 2) + "行文件类型名称为空，";
                    } else {
                        mapConfig.put("tablename", "config_monitor_daily_file_type");
                        mapConfig.put("value", item.getFileTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行文件类型名称在数据库不存在，";
                        } else {
                            item.setFileTypeId(typeId);
                        }
                    }

                    // 重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("serviceId", item.getServiceId());
                    params.put("fileTypeId", item.getFileTypeId());
                    params.put("fileDate", item.getFileDate());

                    if (dailyMonitorMapper.verifyDuplication(params) >= 1) {
                        msg += "第" + (i + 2) + "行【营运单位】+【文件类型】+【文件时间】与数据库中的数据存在重复，";
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

                    for (DailyMonitorExtend dailyMonitorExtend : list) {

                        dailyMonitorExtend.setIsImport(1);
                        dailyMonitorExtend.setCreateDate(new Date());
                        dailyMonitorExtend.setModifyDate(new Date());
                        dailyMonitorExtend.setCreatorId(user.getId());
                        dailyMonitorExtend.setModifyId(user.getId());

                        dailyMonitorMapper.insert(dailyMonitorExtend);

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