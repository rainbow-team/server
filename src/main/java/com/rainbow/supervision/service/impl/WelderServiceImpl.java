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
import com.rainbow.supervision.dao.WelderMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.SupervisionProduceTrain;
import com.rainbow.supervision.domain.Welder;
import com.rainbow.supervision.domain.extend.WelderExtend;
import com.rainbow.supervision.service.WelderService;
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
@Service("SupervisionWelderService")
public class WelderServiceImpl extends BaseService<Welder> implements WelderService {

    @Autowired
    WelderMapper welderMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addWelder(Welder welder) {
        welder.setId(GuidHelper.getGuid());
        welder.setCreateDate(new Date());
        welder.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(welder.getAttachmentList(),welder.getId());
        return welderMapper.insert(welder);
    }

    @Override
    public int modifyWelder(Welder welder) {
        welder.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(welder.getAttachmentList(),welder.getId());
        return welderMapper.updateByPrimaryKey(welder);
    }

    @Override
    public ResponseBo getWelderList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<WelderExtend> list = welderMapper.getWelderList(map);

        PageInfo<WelderExtend> pageInfo = new PageInfo<WelderExtend>(list);

        PagingEntity<WelderExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getWelderById(String id) {
        WelderExtend result = welderMapper.getWelderById(id);

        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败，请重试");
    }

    @Override
    public void exportWelderTrain(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<WelderExtend> list = welderMapper.getWelderList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (WelderExtend welderExtend : list) {
                String[] strs = new String[]{
                        welderExtend.getName(),
                        welderExtend.getIdentity(),
                        welderExtend.getSex()==0?"男":"女",
                        welderExtend.getEmployDepart(),
                        welderExtend.getCertDepart(),
                        welderExtend.getCertNumber(),
                        welderExtend.getSteelNumber(),
                        welderExtend.getExamProject(),
                        DateUtils.DateToString(welderExtend.getExpireDate()),
                        welderExtend.getExamScore(),
                        welderExtend.getExamPlaceValue(),
                        welderExtend.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "姓名",
                "身份证号",
                "性别",
                "聘用单位",
                "发证单位",
                "证书编号",
                "焊工编号",
                "考试合格项目代号",
                "有效期限",
                "考试成绩",
                "考试地点",
                "备注"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "焊接人员资质信息", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("焊接人员资质信息", "utf-8") + ".xls");
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
