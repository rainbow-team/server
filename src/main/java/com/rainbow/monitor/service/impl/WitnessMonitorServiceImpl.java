package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import com.rainbow.monitor.dao.WitnessMonitorMapper;
import com.rainbow.monitor.domain.CheckMonitor;
import com.rainbow.monitor.domain.WitnessMonitor;
import com.rainbow.monitor.domain.extend.WitnessMonitorExtend;
import com.rainbow.monitor.service.CheckMonitorService;
import com.rainbow.monitor.service.WitnessMonitorService;
import com.rainbow.supervision.dao.OrgMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.dao.UmineMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

    @Override
    public int addWitnessMonitor(WitnessMonitor witnessMonitor) {
        witnessMonitor.setId(GuidHelper.getGuid());
        witnessMonitor.setCreateDate(new Date());
        witnessMonitor.setModifyDate(new Date());
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
        List<WitnessMonitor> list = witnessMonitorMapper.getWitnessMonitorList(map);

        PageInfo<WitnessMonitor> pageInfo = new PageInfo<WitnessMonitor>(list);

        PagingEntity<WitnessMonitor> result = new PagingEntity<>(pageInfo);

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