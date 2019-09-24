package com.rainbow.unit.service.impl;

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
import com.rainbow.common.util.Multipart;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.FacReportMapper;
import com.rainbow.unit.dao.GroupMapper;
import com.rainbow.unit.domain.*;
import com.rainbow.unit.service.FacService;
import com.rainbow.unit.service.GroupService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@Service("facservice")
public class FacServiceImpl extends BaseService<Fac> implements FacService {

    @Autowired
    FacMapper facMapper;

    @Autowired
    FacImproveMapper facImproveMapper;

    @Autowired
    FacReportMapper facReportMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Override
    public int addFac(Fac fac) {
        fac.setId(GuidHelper.getGuid());
        fac.setCreateDate(new Date());
        fac.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(fac.getAttachmentList(), fac.getId());
        return facMapper.insert(fac);
    }

    @Override
    public int modifyFac(Fac fac) {
        fac.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(fac.getAttachmentList(), fac.getId());
        return facMapper.updateByPrimaryKey(fac);
    }

    @Override
    public ResponseBo getFacList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FacExtend> list = facMapper.getFacList(map);

        PageInfo<FacExtend> pageInfo = new PageInfo<FacExtend>(list);

        PagingEntity<FacExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getFacById(String id) {
        Fac result = facMapper.getFacById(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public int deleteFacById(String id) {
        Object result = facMapper.getFacRelationCount(id);
        if (result != null) {
            return facMapper.deleteFacById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getFacListByServiceId(String serviceId) {
        List<Fac> result = facMapper.getFacListByServiceId(serviceId);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public void exportFac(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<FacExtend> list = facMapper.getFacList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<FacImprove> facImproveList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (FacExtend facExtend : list) {

                int buildYear = DateUtils.getDateYear(facExtend.getBuildYear());
                String[] strs = new String[] { facExtend.getName(), facExtend.getCode(), facExtend.getServiceDepart(),
                        facExtend.getBuilder(), Integer.toString(buildYear), facExtend.getSupervisionCategoryValue(),
                        facExtend.getTypeValue(), facExtend.getStatusValue(), facExtend.getReviewStatusValue(),
                        facExtend.getPermitSituationValue(), facExtend.getSurvey(), facExtend.getFeature(),
                        facExtend.getIsEarthquake() == 0 ? "不满足" : "满足", facExtend.getIsFlood() == 0 ? "不满足" : "满足",
                        facExtend.getTechDes(), facExtend.getDesignStandardAccident(), facExtend.getMeasure(),
                        facExtend.getNote() };
                cloumnValues.add(strs);

                // 安技改信息
                Map<String, Object> map1 = new HashMap<>();
                map1.put("facId", facExtend.getId());
                List<FacImprove> list1 = facImproveMapper.getFacImproveList(map1);
                if (list1 != null && list1.size() > 0) {
                    facImproveList.addAll(list1);
                }
            }

            String[] cloumnNames = new String[] { "核设施名称", "核设施编号", "核设施营运单位", "参建单位", "建造年代", "监管类别", "设施类型", "设施状态",
                    "审评状态", "许可情况", "设施简介", "场址特征", "是否满足抗震设防登记", "是否满足防洪要求", "工艺描述", "设计基准事故", "工作人员剂量约束", "备注" };

            HSSFWorkbook wb = new HSSFWorkbook();
            wb = ExportExcel.getHssfWorkBook(wb, "核设施信息", cloumnNames, cloumnValues);

            // 安技改信息
            String[] cloumnNames1 = new String[] { "单位名称", "核设施名称", "安技改时间", "安技改内容" };

            cloumnValues = new ArrayList<>();
            if (facImproveList.size() > 0) {
                for (FacImprove facImprove : facImproveList) {

                    String[] strs = new String[] { facImprove.getServiceName(), facImprove.getFacName(),
                            DateUtils.DateToString(facImprove.getImproveDate()), facImprove.getImproveContent() };
                    cloumnValues.add(strs);
                }
            }

            wb = ExportExcel.getHssfWorkBook(wb, "安技改信息", cloumnNames1, cloumnValues);

            try {
                response.setHeader("content-disposition",
                        "attachment;filename=" + URLEncoder.encode("核设施信息", "utf-8") + ".xls");
                OutputStream out = response.getOutputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                wb.write(baos);
                byte[] xlsBytes = baos.toByteArray();
                out.write(xlsBytes);
                out.close();
            } catch (Exception e) {

            }

        }
    }

    @Override
    public ResponseBo importData(HttpServletRequest request) {
        // Multipart part = new Multipart();
        // // 获取前端传过来的file
        // MultipartFile file = part.getUploadFile(request);
        // FileInputStream inputStream = null;
        // FileInputStream inputStream1 = null;

        // String msg = "";

        // try {
        // if (file != null) {
        // // 转化文件名，避免乱码
        // String fileName = new
        // String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
        // inputStream = (FileInputStream) file.getInputStream();
        // inputStream1 = (FileInputStream) file.getInputStream();
        // // 将导入的excel转化为实体
        // List<ServiceDepartExtend> list =
        // ExcelHelper.convertToList(ServiceDepartExtend.class, fileName,
        // inputStream, 2, 14, 0);
        // List<ServiceAnnualReport> serviceAnnualReportList =
        // ExcelHelper.convertToList(ServiceAnnualReport.class,
        // fileName, inputStream1, 2, 2, 1);

        // if (list.size() == 0) {
        // return ResponseBo.error("文件内容为空");
        // }

        // Map<String, String> map = new HashMap<>();
        // // 校验
        // for (int i = 0; i < list.size(); i++) {

        // ServiceDepartExtend serviceDepartExtend = list.get(i);

        // serviceDepartExtend.setId(GuidHelper.getGuid());
        // if (StrUtil.isNullOrEmpty(serviceDepartExtend.getName())) {
        // msg += "第" + (i + 2) + "行单位名称为空，";
        // } else {

        // if (map.containsKey(serviceDepartExtend.getName())) {
        // msg += "第" + (i + 2) + "行单位名称重复，";
        // } else {
        // map.put(serviceDepartExtend.getName(), serviceDepartExtend.getId());
        // }

        // // 校验数据库是否重复
        // int count =
        // serviceDepartMapper.getServiceDepartByName(serviceDepartExtend.getName());
        // if (count > 0) {
        // msg += "第" + (i + 2) + "行单位名称在数据库已存在，";
        // }
        // }

        // if (StrUtil.isNullOrEmpty(serviceDepartExtend.getGroupName())) {
        // msg += "第" + (i + 2) + "行所属集团为空，";
        // } else {

        // String groupId =
        // groupMapper.getGroupIdByName(serviceDepartExtend.getGroupName());

        // if (StrUtil.isNullOrEmpty(groupId)) {
        // msg += "第" + (i + 2) + "行所属集团在数据库中不存在，";
        // } else {
        // serviceDepartExtend.setGroupId(groupId);
        // }

        // }
        // }

        // for (int j = 0; j < serviceAnnualReportList.size(); j++) {

        // ServiceAnnualReport serviceAnnualReport = serviceAnnualReportList.get(j);
        // serviceAnnualReport.setReportId(GuidHelper.getGuid());

        // if (StrUtil.isNullOrEmpty(serviceAnnualReport.getServiceName())) {
        // msg += "年度报告信息第" + (j + 2) + "行单位名称为空，";
        // } else {
        // String serviceId = map.get(serviceAnnualReport.getServiceName());
        // if (StrUtil.isNullOrEmpty(serviceId)) {
        // msg += "年度报告信息第" + (j + 2) + "行单位名称在核设施营运单位信息中不存在，";
        // } else {
        // serviceAnnualReport.setServiceId(serviceId);
        // }
        // }

        // }

        // if (!msg.isEmpty()) {
        // return ResponseBo.error(msg);
        // } else {
        // // 插入数据库
        // SystemUser user = UserUtils.getCurrentUser(cacheManager);
        // if (user == null) {
        // user = new SystemUser();
        // }

        // for (ServiceDepartExtend serviceDepartExtend : list) {

        // serviceDepartExtend.setIsImport(1);
        // serviceDepartExtend.setCreateDate(new Date());
        // serviceDepartExtend.setModifyDate(new Date());
        // serviceDepartExtend.setCreatorId(user.getId());
        // serviceDepartExtend.setModifyId(user.getId());

        // serviceDepartMapper.insert(serviceDepartExtend);
        // }

        // for (ServiceAnnualReport serviceAnnualReport : serviceAnnualReportList) {
        // annualReportMapper.insert(serviceAnnualReport);
        // }
        // }

        // }
        // } catch (Exception e) {
        // return ResponseBo.error(msg);
        // }

        return ResponseBo.ok();
    }
}
