package com.rainbow.monitor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.dao.DailyMonitorMapper;
import com.rainbow.monitor.domain.DailyMonitor;
import com.rainbow.monitor.domain.extend.DailyMonitorExtend;
import com.rainbow.monitor.service.DailyMonitorService;
import com.rainbow.security.domain.extend.EquipSecurityExtend;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("dailymonitorservice")
public class DailyMonitorServiceImpl extends BaseService<DailyMonitor> implements DailyMonitorService {

    @Autowired
    DailyMonitorMapper dailyMonitorMapper;

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
                String[] strs = new String[]{
                        dailyMonitorExtend.getServiceDepartName(),
                        dailyMonitorExtend.getFacName(),
                        dailyMonitorExtend.getStatusValue(),
                        dailyMonitorExtend.getOrgName(),
                        dailyMonitorExtend.getFileTypeValue(),
                        dailyMonitorExtend.getFileName(),
                        DateUtils.DateToString(dailyMonitorExtend.getFileDate())
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "营运单位",
                "设施名称",
                "设施状态",
                "授权监管机构",
                "文件类型",
                "文件名称",
                "文件时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "日常监督信息列表", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("日常监督信息列表", "utf-8") + ".xls");
            OutputStream out = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            wb.write(baos);
            byte[] xlsBytes = baos.toByteArray();
            out.write(xlsBytes);
            out.close();
        }catch (Exception e){

        }
    }
}