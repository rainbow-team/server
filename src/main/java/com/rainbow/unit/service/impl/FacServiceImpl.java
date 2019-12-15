package com.rainbow.unit.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.DateUtils;
import com.rainbow.common.util.ExcelHelper;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.common.util.GuidHelper;
import com.rainbow.common.util.Multipart;
import com.rainbow.common.util.StrUtil;
import com.rainbow.common.util.UserUtils;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.FacImproveMapper;
import com.rainbow.unit.dao.FacMapper;
import com.rainbow.unit.dao.FacReportMapper;
import com.rainbow.unit.dao.ServiceDepartMapper;
import com.rainbow.unit.domain.Fac;
import com.rainbow.unit.domain.FacExtend;
import com.rainbow.unit.domain.FacImprove;
import com.rainbow.unit.service.FacService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("facservice")
public class FacServiceImpl extends BaseService<Fac> implements FacService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    FacMapper facMapper;

    @Autowired
    FacImproveMapper facImproveMapper;

    @Autowired
    FacReportMapper facReportMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    ServiceDepartMapper serviceDepartMapper;

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    CacheManager cacheManager;

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
                        facExtend.getIsEarthquake() == 0 ? "否" : "是", facExtend.getIsFlood() == 0 ? "否" : "是",
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
                List<FacExtend> list = ExcelHelper.convertToList(FacExtend.class, fileName, inputStream, 1, 19, 0);
                List<FacImprove> facImproveList = ExcelHelper.convertToList(FacImprove.class, fileName, inputStream1, 1,
                        3, 1);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }
                if (facImproveList.size() == 0) {
                    return ResponseBo.error("核设施安技改内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    FacExtend item = list.get(i);

                    if (StrUtil.isNullOrEmpty(item.getServiceDepart())) {
                        msg += "第" + (i + 2) + "行营运单位为空,";
                    } else {
                        String serviceDepartId = serviceDepartMapper.getServiceDepartIdByName(item.getServiceDepart());
                        if (StrUtil.isNullOrEmpty(serviceDepartId)) {
                            msg += "第" + (i + 2) + "行营运单位名称在数据库不存在，";
                        } else {
                            item.setServiceId(serviceDepartId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getName())) {
                        msg += "第" + (i + 2) + "行核设施名称为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getSupervisionCategoryValue())) {
                        msg += "第" + (i + 2) + "行监管类别为空，";
                    } else {
                        mapConfig.put("tablename", "config_fac_supervison_category");
                        mapConfig.put("value", item.getSupervisionCategoryValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行监管类别在数据库不存在，";
                        } else {
                            item.setSupervisionCategoryId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getTypeValue())) {
                        msg += "第" + (i + 2) + "行设施类型为空，";
                    } else {
                        mapConfig.put("tablename", "config_fac_type");
                        mapConfig.put("value", item.getTypeValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行设施类型在数据库不存在，";
                        } else {
                            item.setTypeId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getStatusValue())) {
                        msg += "第" + (i + 2) + "行设施状态为空，";
                    } else {
                        mapConfig.put("tablename", "config_fac_status");
                        mapConfig.put("value", item.getStatusValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行设施状态在数据库不存在，";
                        } else {
                            item.setStatusId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getReviewStatusValue())) {
                        msg += "第" + (i + 2) + "行审评状态为空，";
                    } else {
                        mapConfig.put("tablename", "config_review_status");
                        mapConfig.put("value", item.getReviewStatusValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行审评状态在数据库不存在，";
                        } else {
                            item.setReviewStatusId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getPermitSituationValue())) {
                        msg += "第" + (i + 2) + "行许可情况为空，";
                    } else {
                        mapConfig.put("tablename", "config_fac_permit_situation");
                        mapConfig.put("value", item.getPermitSituationValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行许可情况在数据库不存在，";
                        } else {
                            item.setFacPermitSituationId(typeId);
                        }
                    }

                    // Excel数据重复判断
                    if (map.containsKey(item.getServiceId() + item.getName())) {
                        msg += "第" + (i + 2) + "行【单位名称】+【核设施名称】数据重复，";
                    } else {
                        map.put(item.getServiceId() + item.getName(), item.toString());
                    }

                    // 数据库重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("serviceId", item.getServiceId());
                    params.put("name", item.getName());

                    if (facMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "行【单位名称】+【核设施名称】与数据库中的数据存在重复，";
                    }

                    // 获取关联的文件列表并设置ID
                    List<FacImprove> filterList = facImproveList.stream().filter(a -> a.getFacId().equals(item.getId()))
                            .collect(Collectors.toList());

                    if (filterList.size() > 0) {
                        String guid = GuidHelper.getGuid();
                        item.setId(guid);
                        filterList.forEach((cf) -> cf.setFacId(guid));
                    }

                }

                // 核设施安技改
                for (int j = 0; j < facImproveList.size(); j++) {

                    FacImprove item = facImproveList.get(j);

                    if (StrUtil.isNullOrEmpty(item.getFacId())) {
                        msg += "第" + (j + 2) + "行核设施安技改-核设施ID为空，";
                    }
                    if (StrUtil.isNullOrEmpty(item.getImproveContent())) {
                        msg += "第" + (j + 2) + "行核设施安技改-安技改内容为空，";
                    }
                    if (null == item.getImproveDate()) {
                        msg += "第" + (j + 2) + "行核设施安技改-安技改时间为空，";
                    }

                    // 判断安技改是否有对应的核设施，存在未关联情况则提示
                    List<FacExtend> filterList = list.stream().filter(a -> a.getId().equals(item.getFacId()))
                            .collect(Collectors.toList());

                    if (filterList.size() == 0) {
                        msg += "第" + (j + 2) + "行核设施安技改信息没有对应的核设施，";
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

                    for (FacExtend data : list) {

                        data.setIsImport(1);

                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());
                        data.setIsEarthquake("是".equalsIgnoreCase(data.getIsEarthquakeString())?1:0);
                        data.setIsFlood("是".equalsIgnoreCase(data.getIsFloodString())?1:0);

                        facMapper.insert(data);

                    }

                    for (FacImprove facImprove : facImproveList) {
                        // facImprove.setIsImport(1);
                        facImprove.setId(GuidHelper.getGuid());
                        facImproveMapper.insert(facImprove);
                    }
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseBo.error("数据导入失败!\r\t" + msg);
        }

        return ResponseBo.ok();
    }
}