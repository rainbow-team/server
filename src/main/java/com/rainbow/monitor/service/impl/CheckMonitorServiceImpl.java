package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.ActivityCheckMapper;
import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.ExcelHelper;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.common.util.Multipart;
import com.rainbow.common.util.StrUtil;
import com.rainbow.common.util.UserUtils;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.monitor.dao.CheckFileMonitorMapper;
import com.rainbow.monitor.dao.CheckMonitorMapper;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.extend.CheckFileMonitorExtend;
import com.rainbow.monitor.domain.extend.CheckMonitorExtend;
import com.rainbow.monitor.service.CheckMonitorService;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("checkmonitorservice")
public class CheckMonitorServiceImpl extends BaseService<CheckMonitor> implements CheckMonitorService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CheckMonitorMapper checkMonitorMapper;

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

    @Override
    @SystemLog(description = "添加监督检查信息")
    public int addCheckMonitor(CheckMonitor checkMonitor) {
        checkMonitor.setId(GuidHelper.getGuid());
        checkMonitor.setCreateDate(new Date());
        checkMonitor.setModifyDate(new Date());
        return checkMonitorMapper.insert(checkMonitor);
    }

    @Override
    @SystemLog(description = "修改监督检查信息")
    public int modifyCheckMonitor(CheckMonitor checkMonitor) {
        checkMonitor.setModifyDate(new Date());
        return checkMonitorMapper.updateByPrimaryKey(checkMonitor);
    }

    @Override
    @SystemLog(description = "删除监督检查信息")
    public int deleteCheckMonitorById(String id) {
        Object result = checkMonitorMapper.getCheckMonitorRelationCount(id);
        if (result != null) {
            return checkMonitorMapper.deleteCheckMonitorById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getCheckMonitorList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<CheckMonitor> list = checkMonitorMapper.getCheckMonitorList(map);

        PageInfo<CheckMonitor> pageInfo = new PageInfo<CheckMonitor>(list);

        PagingEntity<CheckMonitor> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getCheckMonitorById(String id) {
        CheckMonitor result = checkMonitorMapper.getCheckMonitorById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
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
                List<CheckMonitorExtend> list = ExcelHelper.convertToList(CheckMonitorExtend.class, fileName,
                        inputStream, 2, 9, 0);
                List<CheckFileMonitorExtend> checkFileMonitorExtendList = ExcelHelper
                        .convertToList(CheckFileMonitorExtend.class, fileName, inputStream1, 2, 4, 1);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    CheckMonitorExtend item = list.get(i);

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

                    if (StrUtil.isNullOrEmpty(item.getContent())) {
                        msg += "第" + (i + 2) + "行文件名称为空,";
                    }

                    if (item.getStartDate() == null || item.getEndDate() == null) {
                        msg += "第" + (i + 2) + "行文件时间为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getTypeValue())) {
                        msg += "第" + (i + 2) + "行文件类型名称为空，";
                    } else {
                        mapConfig.put("tablename", "config_monitor_check_type");
                        mapConfig.put("value", item.getTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行文件类型名称在数据库不存在，";
                        } else {
                            item.setTypeId(typeId);
                        }
                    }

                    // 重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("umineId", item.getUmineId());
                    params.put("serviceId", item.getServiceId());
                    params.put("equipDepartId", item.getEquipDepartId());
                    params.put("content", item.getContent());
                    params.put("typeId", item.getTypeId());
                    params.put("startDate", item.getStartDate());
                    params.put("endDate", item.getEndDate());

                    if (checkMonitorMapper.verifyDuplication(params) >= 1) {
                        msg += "第" + (i + 2) + "行单位名称】+【检查内容】+【检查类型】+【检查时间】与数据库中的数据存在重复，";
                    }

                    // 获取关联的文件列表
                    List<CheckFileMonitorExtend> filterList = checkFileMonitorExtendList.stream()
                            .filter(a -> a.getMonitorCheckId().equals(item.getId())).collect(Collectors.toList());
                    String guid = GuidHelper.getGuid();
                    if (filterList.size() == 0) {
                        msg += "监督检查文件ID列存在未关联数据，";
                    } else {
                        item.setId(guid);
                        filterList.forEach((cf) -> cf.setMonitorCheckId(guid));
                    }

                }

                // 校验监督检查文件
                for (int j = 0; j < checkFileMonitorExtendList.size(); j++) {

                    CheckFileMonitorExtend checkFileMonitorExtend = checkFileMonitorExtendList.get(j);
                    // checkFileMonitorExtend.setId(GuidHelper.getGuid());

                    if (checkFileMonitorExtend.getFileDate() == null) {
                        msg += "监督检查文件信息第" + (j + 2) + "文件时间为空，";
                    }

                    if (StrUtil.isNullOrEmpty(checkFileMonitorExtend.getTypeValue())) {
                        msg += "第" + (j + 2) + "行文件类型名称为空，";
                    } else {
                        mapConfig.put("tablename", "config_monitor_check_file_type");
                        mapConfig.put("value", checkFileMonitorExtend.getTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (j + 2) + "行文件类型名称在数据库不存在，";
                        } else {
                            checkFileMonitorExtend.setMonitorCheckFileTypeId(typeId);
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

                    for (CheckMonitorExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModify(user.getId());

                        checkMonitorMapper.insert(data);

                    }

                    for (CheckFileMonitorExtend ckFile : checkFileMonitorExtendList) {
                        ckFile.setIsImport(1);
                        checkFileMonitorMapper.insert(ckFile);
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