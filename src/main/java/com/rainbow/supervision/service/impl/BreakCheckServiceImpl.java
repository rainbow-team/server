package com.rainbow.supervision.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.supervision.dao.BreakCheckerMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.SupervisionProduceTrain;
import com.rainbow.supervision.domain.extend.BreakCheckerExtend;
import com.rainbow.supervision.service.BreakCheckerService;
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
@Service("breakCheckerService")
public class BreakCheckServiceImpl extends BaseService<BreakChecker> implements BreakCheckerService {

    @Autowired
    BreakCheckerMapper breakCheckerMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addBreakChecker(BreakChecker breakChecker) {
        breakChecker.setId(GuidHelper.getGuid());
        breakChecker.setCreateDate(new Date());
        breakChecker.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(breakChecker.getAttachmentList(),breakChecker.getId());
        return breakCheckerMapper.insert(breakChecker);
    }

    @Override
    public int modifyBreakChecker(BreakChecker breakChecker) {
        breakChecker.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(breakChecker.getAttachmentList(),breakChecker.getId());
        return breakCheckerMapper.updateByPrimaryKey(breakChecker);
    }

    @Override
    public ResponseBo getBreakCheckerList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<BreakCheckerExtend> list = breakCheckerMapper.getBreakCheckerList(map);

        PageInfo<BreakCheckerExtend> pageInfo = new PageInfo<BreakCheckerExtend>(list);

        PagingEntity<BreakCheckerExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getBreakCheckerById(String id) {

        BreakChecker result = breakCheckerMapper.getBreakCheckerById(id);

        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败，请重试");
    }

    @Override
    public void exportBreakChecker(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<BreakCheckerExtend> list = breakCheckerMapper.getBreakCheckerList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (BreakCheckerExtend breakCheckerExtend : list) {
                String[] strs = new String[]{
                        breakCheckerExtend.getName(),
                        breakCheckerExtend.getIdentity(),
                        breakCheckerExtend.getSex()==0?"男":"女",
                        breakCheckerExtend.getEmployDepart(),
                        breakCheckerExtend.getCheckMethodValue(),
                        breakCheckerExtend.getCheckLevelValue(),
                        DateUtils.DateToString(breakCheckerExtend.getValidDate()),
                        breakCheckerExtend.getCertNumber(),
                        breakCheckerExtend.getCertDepart(),
                        DateUtils.DateToString(breakCheckerExtend.getCertDate()),
                        breakCheckerExtend.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "姓名",
                "身份证号",
                "性别",
                "聘用单位",
                "检验方法",
                "等级",
                "有效期限",
                "证书编号",
                "发证单位",
                "发证日期",
                "备注"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "无损检验人员资质信息", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("无损检验人员资质信息", "utf-8") + ".xls");
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
