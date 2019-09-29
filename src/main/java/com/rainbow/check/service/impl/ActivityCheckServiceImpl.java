package com.rainbow.check.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.check.dao.ActivityCheckMapper;
import com.rainbow.check.dao.ActivityFileCheckMapper;
import com.rainbow.check.dao.EquipCheckMapper;
import com.rainbow.check.domain.ActivityCheck;
import com.rainbow.check.domain.ActivityCheckExtend;
import com.rainbow.check.domain.ActivityFileCheckExtend;
import com.rainbow.check.domain.EquipCheck;
import com.rainbow.check.service.ActivityCheckService;
import com.rainbow.check.service.EquipCheckService;
import com.rainbow.common.annotation.SystemLog;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.monitor.domain.extend.CheckFileMonitorExtend;
import com.rainbow.monitor.domain.extend.CheckMonitorExtend;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("activitycheckservice")
public class ActivityCheckServiceImpl extends BaseService<ActivityCheck> implements ActivityCheckService {

    @Autowired
    ActivityCheckMapper activityCheckMapper;

    @Autowired
    ActivityFileCheckMapper activityFileCheckMapper;

    @Override
    public int addActivityCheck(ActivityCheck activityCheck) {
        activityCheck.setId(GuidHelper.getGuid());
        activityCheck.setCreateDate(new Date());
        activityCheck.setModifyDate(new Date());
        return activityCheckMapper.insert(activityCheck);
    }

    @Override
    public int modifyActivityCheck(ActivityCheck activityCheck) {
        activityCheck.setModifyDate(new Date());
        return activityCheckMapper.updateByPrimaryKey(activityCheck);
    }

    @Override
    public int deleteActivityCheckById(String id) {
        Object result = activityCheckMapper.getActivityCheckRelationCount(id);
        if (result != null) {
            return activityCheckMapper.deleteActivityCheckById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getActivityCheckList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ActivityCheckExtend> list = activityCheckMapper.getActivityCheckList(map);

        PageInfo<ActivityCheckExtend> pageInfo = new PageInfo<ActivityCheckExtend>(list);

        PagingEntity<ActivityCheckExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getActivityCheckById(String id) {
        ActivityCheck result = activityCheckMapper.getActivityCheckById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportActivityCheck(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<ActivityCheckExtend> list = activityCheckMapper.getActivityCheckList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<ActivityFileCheckExtend> activityFileCheckExtendList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (ActivityCheckExtend activityCheckExtend : list) {

                StringBuffer buf=new StringBuffer();
                buf.append(activityCheckExtend.getServiceDepartName() == null ? "" : activityCheckExtend.getServiceDepartName())
                        .append(activityCheckExtend.getEquipDepartName() == null ? "" : activityCheckExtend.getEquipDepartName());

                String[] strs = new String[]{
                        activityCheckExtend.getId(),
                        buf.toString(),
                        activityCheckExtend.getFacName(),
                        activityCheckExtend.getCheckContent(),
                        activityCheckExtend.getTypeValue(),
                        DateUtils.DateToString(activityCheckExtend.getCheckDate())
                };
                cloumnValues.add(strs);

                //培训信息
                Map<String,Object> map1  = new HashMap<>();
                map1.put("activityId",activityCheckExtend.getId());
                List<ActivityFileCheckExtend> list1 = activityFileCheckMapper.getActivityFileCheckList(map1);
                if(list1!=null&&list1.size()>0){
                    activityFileCheckExtendList.addAll(list1);
                }
            }
        }

        String[] cloumnNames = new String[]{
                "主编号",
                "单位名称",
                "设施名称",
                "审评(查)内容",
                "活动类型",
                "审评时间"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核活动及其他审评信息列表", cloumnNames, cloumnValues);


        //培审评文件信息
        String[] cloumnNames1 = new String[]{
                "主编号",
                "文件类型",
                "文件文号",
                "文件时间"
        };

        cloumnValues = new ArrayList<>();
        if(activityFileCheckExtendList.size()>0){
            for (ActivityFileCheckExtend activityFileCheckExtend : activityFileCheckExtendList) {
                String[] strs = new String[]{
                        activityFileCheckExtend.getCheckActivityId(),
                        activityFileCheckExtend.getActivityCheckFileTypeValue(),
                        activityFileCheckExtend.getFileNo(),
                        DateUtils.DateToString(activityFileCheckExtend.getFileDate())
                };
                cloumnValues.add(strs);
            }
        }

        wb = ExportExcel.getHssfWorkBook(wb, "审评文件列表", cloumnNames1, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核活动及其他审评信息列表", "utf-8") + ".xls");
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