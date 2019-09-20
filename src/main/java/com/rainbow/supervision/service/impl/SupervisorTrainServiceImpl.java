package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.SupervisionTrainRecordMapper;
import com.rainbow.supervision.dao.SupervisorTrainMapper;
import com.rainbow.supervision.domain.SupervisionTrainRecordExtend;
import com.rainbow.supervision.domain.SupervisorTrain;
import com.rainbow.supervision.domain.extend.OrgExtend;
import com.rainbow.supervision.service.SupervisorTrainService;
import com.rainbow.system.dao.UserMapper;
import com.rainbow.system.domain.SystemUser;
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
@Service("SupervisionMonitorTrainService")
public class SupervisorTrainServiceImpl extends BaseService<SupervisorTrain> implements SupervisorTrainService {

    @Autowired
    SupervisorTrainMapper monitorTrainMapper;

    @Autowired
    private com.rainbow.attachment.dao.FileInfoMapper FileInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SupervisionTrainRecordMapper supervisionTrainRecordMapper;

    @Override
    public int addMonitorTrain(SupervisorTrain trainRecord) {
        trainRecord.setId(GuidHelper.getGuid());
        trainRecord.setCreateDate(new Date());
        trainRecord.setModifyDate(new Date());

        updateFileInfoByIds(trainRecord);

        return monitorTrainMapper.insert(trainRecord);
    }

    @Override
    public int modifyMonitorTrain(SupervisorTrain trainRecord) {
        trainRecord.setModifyDate(new Date());

        updateFileInfoByIds(trainRecord);
        return monitorTrainMapper.updateByPrimaryKey(trainRecord);
    }

    public void updateFileInfoByIds(SupervisorTrain trainRecord) {
        if (trainRecord.getAttachmentList().size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", trainRecord.getId());
            map.put("fileIds", trainRecord.getAttachmentList());
            FileInfoMapper.updateFileInfoByIds(map);
        }
    }

    @Override
    public ResponseBo getMonitorTrainList(Page page) {

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisorTrain> list = monitorTrainMapper.getMonitorTrainList(map);

        PageInfo<SupervisorTrain> pageInfo = new PageInfo<SupervisorTrain>(list);

        PagingEntity<SupervisorTrain> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getMonitorTrainById(String id) {

        SupervisorTrain result = monitorTrainMapper.selectByPrimaryKey(id);
        ////创建人
        //SystemUser user = userMapper.selectByPrimaryKey(result.getCreatorId());
        //result.setCreatorName(user.getUsername());

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo deleteMonitorTrainById(String id) {
        int result = 0;
        int count = supervisionTrainRecordMapper.getRecordCountByClassId(id);
        if (count == 0) {
            result = super.deleteByKey(id);
        } else {
            return ResponseBo.error("已经被关联，删除失败!");
        }
        return result == 0 ? ResponseBo.error("删除失败") : ResponseBo.ok("删除成功");
    }

    @Override
    public void exportMonitorTrain(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<SupervisorTrain> list = monitorTrainMapper.getMonitorTrainList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<SupervisorTrain> supervisorTrainList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (SupervisorTrain supervisorTrain : list) {
                String[] strs = new String[]{
                        supervisorTrain.getBatch(),
                        DateUtils.DateToString(supervisorTrain.getBeginDate()),
                        DateUtils.DateToString(supervisorTrain.getEndDate()),
                        supervisorTrain.getPlace(),
                        supervisorTrain.getNumber().toString(),
                        supervisorTrain.getContent(),
                        supervisorTrain.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "培训班次",
                "培训开始日期",
                "培训结束日期",
                "培训地点",
                "参培人数",
                "培训内容及教师",
                "备注"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "核安全监督培训信息", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("核安全监督培训信息", "utf-8") + ".xls");
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
