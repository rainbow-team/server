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
import com.rainbow.supervision.dao.OperatorLisenceMapper;
import com.rainbow.supervision.domain.BreakChecker;
import com.rainbow.supervision.domain.OperatorLisence;
import com.rainbow.supervision.domain.extend.BreakCheckerExtend;
import com.rainbow.supervision.domain.extend.OperatorLisenceExtend;
import com.rainbow.supervision.service.BreakCheckerService;
import com.rainbow.supervision.service.OperatorLisenceService;
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
@Service("operatorLisenceService")
public class OperatorLisenceServiceImpl extends BaseService<OperatorLisence> implements OperatorLisenceService {

    @Autowired
    OperatorLisenceMapper operatorLisenceMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addOperatorLisence(OperatorLisence operatorLisence) {
        operatorLisence.setId(GuidHelper.getGuid());
        operatorLisence.setCreateDate(new Date());
        operatorLisence.setModifyDate(new Date());
        fileInfoService.updateFileInfoByIds(operatorLisence.getAttachmentList(),operatorLisence.getId());
        return operatorLisenceMapper.insert(operatorLisence);
    }

    @Override
    public int modifyOperatorLisence(OperatorLisence operatorLisence) {
        operatorLisence.setModifyDate(new Date());
        return operatorLisenceMapper.updateByPrimaryKey(operatorLisence);
    }

    @Override
    public ResponseBo getOperatorLisenceList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<OperatorLisenceExtend> list = operatorLisenceMapper.getOperatorLisenceList(map);

        PageInfo<OperatorLisenceExtend> pageInfo = new PageInfo<OperatorLisenceExtend>(list);

        PagingEntity<OperatorLisenceExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getOperatorLisenceById(String id) {
        OperatorLisence result = operatorLisenceMapper.getOperatorLisenceById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("获取失败，请重试");
    }

    @Override
    public void exportOperator(Page page, HttpServletResponse response) {
        Map<String, Object> map = page.getQueryParameter();
        List<OperatorLisenceExtend> list = operatorLisenceMapper.getOperatorLisenceList(map);

        List<String[]> cloumnValues = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (OperatorLisenceExtend operatorLisenceExtend : list) {
                String[] strs = new String[]{
                        operatorLisenceExtend.getName(),
                        operatorLisenceExtend.getIdentity(),
                        operatorLisenceExtend.getEmployDepart(),
                        operatorLisenceExtend.getHeapName(),
                        operatorLisenceExtend.getLicenseTypeValue(),
                        operatorLisenceExtend.getLicenseNumber(),
                        operatorLisenceExtend.getCertDepart(),
                        DateUtils.DateToString(operatorLisenceExtend.getCertDate()),
                        DateUtils.DateToString(operatorLisenceExtend.getExpireDate()),
                        operatorLisenceExtend.getNote()
                };
                cloumnValues.add(strs);
            }
        }

        String[] cloumnNames = new String[]{
                "姓名",
                "身份证号",
                "聘用单位",
                "研究堆名称",
                "执照种类",
                "执照编号",
                "发证单位",
                "发证日期",
                "有效期限",
                "备注"
        };

        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExportExcel.getHssfWorkBook(wb, "研究堆操纵员执照信息", cloumnNames, cloumnValues);


        try{
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("研究堆操纵员执照信息", "utf-8") + ".xls");
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
