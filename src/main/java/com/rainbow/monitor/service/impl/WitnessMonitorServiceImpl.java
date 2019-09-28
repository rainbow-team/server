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
import com.rainbow.monitor.dao.CheckFileMonitorMapper;
import com.rainbow.monitor.dao.CheckMonitorMapper;
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.domain.extend.DailyMonitorExtend;
import com.rainbow.monitor.domain.extend.WitnessMonitorExtend;
import com.rainbow.monitor.service.CheckMonitorService;
import com.rainbow.monitor.service.WitnessMonitorService;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;

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
@Service("witnessmonitorservice")
public class WitnessMonitorServiceImpl extends BaseService<WitnessMonitor> implements WitnessMonitorService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CheckFileMonitorMapper checkFileMonitorMapper;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    UmineMapper umineMapper;

    @Autowired
    EquipDepartMapper equipDepartMapper;

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    CacheManager cacheManager;
    @Autowired
    WitnessMonitorMapper witnessMonitorMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addWitnessMonitor(WitnessMonitor witnessMonitor) {
        witnessMonitor.setId(GuidHelper.getGuid());
        witnessMonitor.setCreateDate(new Date());
        witnessMonitor.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(witnessMonitor.getAttachmentList(),witnessMonitor.getId());
        return witnessMonitorMapper.insert(witnessMonitor);
    }

    @Override
    public int modifyWitnessMonitor(WitnessMonitor witnessMonitor) {
        witnessMonitor.setModifyDate(new Date());
        return witnessMonitorMapper.updateByPrimaryKey(witnessMonitor);
    }

    @Override
    public ResponseBo getWitnessMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<WitnessMonitorExtend> list = witnessMonitorMapper.getWitnessMonitorList(map);

        PageInfo<WitnessMonitorExtend> pageInfo = new PageInfo<WitnessMonitorExtend>(list);

        PagingEntity<WitnessMonitorExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getWitnessMonitorById(String id) {
        WitnessMonitor result = witnessMonitorMapper.getWitnessMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportWitnessMonitor(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<WitnessMonitorExtend> list = witnessMonitorMapper.getWitnessMonitorList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (WitnessMonitorExtend witnessMonitorExtend : list) {
                StringBuffer buf=new StringBuffer();
                buf.append(witnessMonitorExtend.getServiceDepartName() == null ? "" : witnessMonitorExtend.getServiceDepartName())
                .append(witnessMonitorExtend.getUmineName() == null ? "" : witnessMonitorExtend.getUmineName())
                        .append(witnessMonitorExtend.getEquipDepartName() == null ? "" : witnessMonitorExtend.getEquipDepartName());
//                String name = witnessMonitorExtend.getServiceDepartName() == null ? "" : witnessMonitorExtend.getServiceDepartName()
//                        + witnessMonitorExtend.getUmineName() == null ? "" : witnessMonitorExtend.getUmineName()
//                        + witnessMonitorExtend.getEquipDepartName() == null ? "" : witnessMonitorExtend.getEquipDepartName();
                String[] strs = new String[]{
                        buf.toString(),
                        witnessMonitorExtend.getWitnessObject(), witnessMonitorExtend.getWitnessItems(),
                        DateUtils.DateToString(witnessMonitorExtend.getWitnessDate()),
                        witnessMonitorExtend.getWitnessResult(), witnessMonitorExtend.getWitnessQuestion(),
                        witnessMonitorExtend.getReform(), witnessMonitorExtend.getWitness()};
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[] { "单位名称", "见证对象", "见证事项", "见证时间", "见证结论", "存在问题", "整改情况","见证人" };

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
        // FileInputStream inputStream1 = null;

        String msg = "";
        try {
            if (file != null) {
                // 转化文件名，避免乱码
                String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                inputStream = (FileInputStream) file.getInputStream();
                // inputStream1 = (FileInputStream) file.getInputStream();

                // 将导入的excel转化为实体
                List<WitnessMonitorExtend> list = ExcelHelper.convertToList(WitnessMonitorExtend.class, fileName,
                        inputStream, 2, 9, 0);
                // List<CheckFileMonitorExtend> checkFileMonitorExtendList =
                // ExcelHelper.convertToList(CheckFileMonitorExtend.class, fileName,
                // inputStream1, 2, 4,1);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    WitnessMonitorExtend item = list.get(i);
                    item.setId(GuidHelper.getGuid());
                    if (StrUtil.isNullOrEmpty(item.getServiceDepartName()) && StrUtil.isNullOrEmpty(item.getUmineName())
                            && StrUtil.isNullOrEmpty(item.getEquipDepartName())) {
                        msg += "第" + (i + 2) + "行营运单位为空,";
                    } else {
                        if (!StrUtil.isNullOrEmpty(item.getServiceDepartName())) {
                            String serviceDepartId = serviceDepartMapper
                                    .getServiceDepartIdByName(item.getServiceDepartName());
                            if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                                msg += "第" + (i + 2) + "行营运单位名称在数据库不存在，";
                            } else {
                                item.setServiceId(serviceDepartId);
                            }
                        }
                        if (!StrUtil.isNullOrEmpty(item.getUmineName())) {
                            String umineId = umineMapper.getUmineIdByName(item.getUmineName());
                            if (StrUtil.isNullOrEmpty(umineId)) {
                                msg += "第" + (i + 2) + "行营运单位名称在数据库不存在，";
                            } else {
                                item.setUmineId(umineId);
                            }
                        }

                        if (!StrUtil.isNullOrEmpty(item.getEquipDepartName())) {
                            String equipDepartId = equipDepartMapper.getEquipDepartIdByName(item.getEquipDepartName());
                            if (StrUtil.isNullOrEmpty(equipDepartId)) {
                                msg += "第" + (i + 2) + "行营运单位名称在数据库不存在，";
                            } else {
                                item.setEquipDepartId(equipDepartId);
                            }
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getWitnessItems())) {
                        msg += "第" + (i + 2) + "行见证事项为空,";
                    }

                    if (item.getWitnessDate() == null) {
                        msg += "第" + (i + 2) + "行见证时间为空,";
                    }

                    // Excel数据重复判断
                    if (map.containsKey(item.getUmineId() + item.getServiceId() + item.getEquipDepartId()
                            + item.getWitnessItems() + item.getWitnessDate().toString())) {
                        msg += "第" + (i + 2) + "行【单位名称】+【见证事项】+【见证时间】数据重复，";
                    } else {
                        map.put(item.getUmineId() + item.getServiceId() + item.getEquipDepartId()
                                + item.getWitnessItems() + item.getWitnessDate().toString(), item.toString());
                    }
                    // 重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("umineId", item.getUmineId());
                    params.put("serviceId", item.getServiceId());
                    params.put("equipDepartId", item.getEquipDepartId());
                    params.put("witnessItems", item.getWitnessItems());
                    params.put("witnessDate", item.getWitnessDate());

                    if (witnessMonitorMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "行【单位名称】+【见证事项】+【见证时间】与数据库中的数据存在重复，";
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

                    for (WitnessMonitorExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        witnessMonitorMapper.insert(data);

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