package com.rainbow.unit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainbow.attachment.service.FileInfoService;
import com.rainbow.common.domain.Page;
import com.rainbow.common.domain.PagingEntity;
import com.rainbow.common.domain.ResponseBo;
import com.rainbow.common.service.impl.BaseService;
import com.rainbow.common.util.*;
import com.rainbow.config.dao.SystemConfigMapper;
import com.rainbow.system.domain.SystemUser;
import com.rainbow.unit.dao.EquipDepartMapper;
import com.rainbow.unit.dao.UmineMapper;
import com.rainbow.unit.dao.UminePlaceImproveMapper;
import com.rainbow.unit.dao.UmineplaceMapper;
import com.rainbow.unit.domain.*;
import com.rainbow.unit.service.EquipDepartService;
import com.rainbow.unit.service.UmineplaceService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.ehcache.CacheManager;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:deepblue
 * @Date:2019/5/5 13:11
 * @Description:
 **/
@Service("umineplaceservice")
public class UmineplaceServiceImpl extends BaseService<Umineplace> implements UmineplaceService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SystemConfigMapper systemConfigMapper;

    @Autowired
    CacheManager cacheManager;
    @Autowired
    UmineplaceMapper umineplaceMapper;

    @Autowired
    UminePlaceImproveMapper uminePlaceImproveMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    UmineMapper umineMapper;

    @Override
    public int addUmineplace(Umineplace umineplace) {
        umineplace.setId(GuidHelper.getGuid());
        umineplace.setCreateDate(new Date());
        umineplace.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umineplace.getAttachmentList(), umineplace.getId());
        return umineplaceMapper.insert(umineplace);
    }

    @Override
    public int modifyUmineplace(Umineplace umineplace) {
        umineplace.setModifyDate(new Date());

        fileInfoService.updateFileInfoByIds(umineplace.getAttachmentList(), umineplace.getId());
        return umineplaceMapper.updateByPrimaryKey(umineplace);
    }

    @Override
    public ResponseBo getUmineplaceList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<UmineplaceExtend> list = umineplaceMapper.getUmineplaceList(map);

        PageInfo<UmineplaceExtend> pageInfo = new PageInfo<UmineplaceExtend>(list);

        PagingEntity<UmineplaceExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getUmineplaceById(String id) {
        Umineplace result = umineplaceMapper.getUmineplaceById(id);
        return ResponseBo.ok(result);
    }

    @Override
    public int deleteUmineplaceById(String id) {
        Object result = umineplaceMapper.getUmineplaceRelationCount(id);
        if (result != null) {
            return umineplaceMapper.deleteUmineplaceById(id);
        }
        return 0;
    }

    @Override
    public ResponseBo getUmineplaceListByUmineId(String umineId) {
        List<Umineplace> result = umineplaceMapper.getUmineplaceListByUmineId(umineId);
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
                List<UmineplaceExtend> list = ExcelHelper.convertToList(UmineplaceExtend.class, fileName, inputStream,
                        1, 19, 0);
                List<UminePlaceImprove> uminePlaceImproveList = ExcelHelper.convertToList(UminePlaceImprove.class,
                        fileName, inputStream1, 1, 3, 1);

                if (list.size() == 0) {
                    return ResponseBo.error("文件内容为空");
                }

                Map<String, String> map = new HashMap<>();
                Map<String, String> mapConfig = new HashMap<>();
                // 校验
                for (int i = 0; i < list.size(); i++) {
                    UmineplaceExtend item = list.get(i);

                    if (StrUtil.isNullOrEmpty(item.getUmineName())) {
                        msg += "第" + (i + 2) + "行营运单位为空,";
                    } else {
                        String umineId = umineMapper.getUmineIdByName(item.getUmineName());
                        if (StrUtil.isNullOrEmpty(umineId)) {
                            msg += "第" + (i + 2) + "行营运单位名称在数据库不存在，";
                        } else {
                            item.setUmineId(umineId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getName())) {
                        msg += "第" + (i + 2) + "行铀尾矿(渣)库名称为空,";
                    }
                    if (null == item.getBuildYear()) {
                        msg += "第" + (i + 2) + "行建造年代为空,";
                    }

                    if (StrUtil.isNullOrEmpty(item.getLevelValue())) {
                        msg += "第" + (i + 2) + "行铀尾矿(渣)库等别为空，";
                    } else {
                        mapConfig.put("tablename", "config_umine_place_level");
                        mapConfig.put("value", item.getLevelValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行铀尾矿(渣)库等别在数据库不存在，";
                        } else {
                            item.setLevelId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getStatusValue())) {
                        msg += "第" + (i + 2) + "行设施状态为空，";
                    } else {
                        mapConfig.put("tablename", "config_umine_place_status");
                        mapConfig.put("value", item.getStatusValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行设施状态在数据库不存在，";
                        } else {
                            item.setStatusId(typeId);
                        }
                    }

                    if (StrUtil.isNullOrEmpty(item.getReviewStatus())) {
                        msg += "第" + (i + 2) + "行审评状态为空，";
                    } else {
                        mapConfig.put("tablename", "config_review_status");
                        mapConfig.put("value", item.getReviewStatus());
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
                        mapConfig.put("tablename", "config_umine_place_permit_situation");
                        mapConfig.put("value", item.getPermitSituationValue());
                        String typeId = systemConfigMapper.getConfigIdByName(mapConfig);

                        if (StrUtil.isNullOrEmpty(typeId)) {
                            msg += "第" + (i + 2) + "行许可情况在数据库不存在，";
                        } else {
                            item.setPermitSituationId(typeId);
                        }
                    }

                    // if (StrUtil.isNullOrEmpty(item.getIsEarthquake())) {
                    // msg += "第" + (i + 2) + "行文件类型名称为空，";
                    // }
                    // if (StrUtil.isNullOrEmpty(item.getIsFlood())) {
                    // msg += "第" + (i + 2) + "行文件类型名称为空，";
                    // }

                    // Excel数据重复判断
                    if (map.containsKey(item.getUmineId() + item.getName())) {
                        msg += "第" + (i + 2) + "行【单位名称】+【铀尾矿（渣）库名称】数据重复，";
                    } else {
                        map.put(item.getUmineId() + item.getName(), item.toString());
                    }

                    // 数据库重复判断
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("umineId", item.getUmineId());
                    params.put("name", item.getName());

                    if (umineplaceMapper.verifyDuplication(params) > 0) {
                        msg += "第" + (i + 2) + "【单位名称】+【铀尾矿（渣）库名称】与数据库中的数据存在重复，";
                    }

                    // 获取关联的文件列表
                    List<UminePlaceImprove> filterList = uminePlaceImproveList.stream()
                            .filter(a -> a.getUminePlaceId().equals(item.getId())).collect(Collectors.toList());

                    if (filterList.size() == 0) {
                        msg += "第" + (i + 2) + "核设施安技改ID列存在未关联数据情况，";
                    } else {
                        String guid = GuidHelper.getGuid();
                        item.setId(guid);
                        filterList.forEach((cf) -> cf.setUminePlaceId(guid));
                    }

                }

                // 核设施安技改
                for (int j = 0; j < uminePlaceImproveList.size(); j++) {

                    UminePlaceImprove item = uminePlaceImproveList.get(j);

                    if (StrUtil.isNullOrEmpty(item.getUminePlaceId())) {
                        msg += "第" + (j + 2) + "行核设施安技改-核设施名称为空，";
                    }
                    if (StrUtil.isNullOrEmpty(item.getImproveContent())) {
                        msg += "第" + (j + 2) + "行核设施安技改-安技改内容为空，";
                    }
                    if (null == item.getImproveDate()) {
                        msg += "第" + (j + 2) + "行核设施安技改-安技改时间为空，";
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

                    for (UmineplaceExtend data : list) {

                        data.setIsImport(1);
                        data.setCreateDate(new Date());
                        data.setModifyDate(new Date());
                        data.setCreatorId(user.getId());
                        data.setModifyId(user.getId());

                        umineplaceMapper.insert(data);

                    }

                    for (UminePlaceImprove uminePlaceImprove : uminePlaceImproveList) {
                        // UminePlaceImprove.setIsImport(1);
                        uminePlaceImprove.setId(GuidHelper.getGuid());
                        uminePlaceImproveMapper.insert(uminePlaceImprove);
                    }
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseBo.error(msg);
        }

        return ResponseBo.ok();
    }

    @Override
    public void exportUminePlace(Page page, HttpServletResponse response) {

        Map<String, Object> map = page.getQueryParameter();
        List<UmineplaceExtend> list = umineplaceMapper.getUmineplaceList(map);

        List<String[]> cloumnValues = new ArrayList<>();
        List<UminePlaceImprove> UminePlaceImproveList = new ArrayList<>();

        if (list != null && list.size() > 0) {

            for (UmineplaceExtend umineplaceExtend : list) {

                int buildYear = DateUtils.getDateYear(umineplaceExtend.getBuildYear());
                String[] strs = new String[] { umineplaceExtend.getName(), umineplaceExtend.getUmineName(),
                        Integer.toString(buildYear), umineplaceExtend.getLevelValue(),
                        umineplaceExtend.getStatusValue(), umineplaceExtend.getReviewStatus(),
                        umineplaceExtend.getPermitSituationValue(), umineplaceExtend.getSurvey(),
                        umineplaceExtend.getFeature(), umineplaceExtend.getCapacity(),
                        umineplaceExtend.getDesignFloodReproduce(), umineplaceExtend.getCheckFloodReproduce(),
                        umineplaceExtend.getEarlyDamType(), umineplaceExtend.getEarlyDamHeight(),
                        umineplaceExtend.getHaveMonitor() == 0 ? "否" : "是", umineplaceExtend.getNote() };
                cloumnValues.add(strs);

                // 安技改信息
                Map<String, Object> map1 = new HashMap<>();
                map1.put("uminePlaceId", umineplaceExtend.getId());
                List<UminePlaceImprove> list1 = uminePlaceImproveMapper.getUminePlaceImproveList(map1);
                if (list1 != null && list1.size() > 0) {
                    UminePlaceImproveList.addAll(list1);
                }
            }

            String[] cloumnNames = new String[] { "铀尾矿(渣)库名称", "营运单位", "建造年代", "铀尾矿(渣)库等别", "铀尾矿(库)设施状态", "审评状态",
                    "铀尾矿(渣)库许可情况", "设施简介", "场址特征", "设计有效库容", "设计洪水重现期", "校核洪水重现期", "初期坝型", "初期坝高", "是否设置坝体监测设施", "备注" };

            HSSFWorkbook wb = new HSSFWorkbook();
            wb = ExportExcel.getHssfWorkBook(wb, "铀尾矿（渣）库信息", cloumnNames, cloumnValues);

            // 安技改信息
            String[] cloumnNames1 = new String[] { "铀尾矿(渣)库名称", "安技改时间", "安技改内容" };

            cloumnValues = new ArrayList<>();
            if (UminePlaceImproveList.size() > 0) {
                for (UminePlaceImprove uminePlaceImprove : UminePlaceImproveList) {

                    String[] strs = new String[] { uminePlaceImprove.getUminePlaceName(),
                            DateUtils.DateToString(uminePlaceImprove.getImproveDate()),
                            uminePlaceImprove.getImproveContent() };
                    cloumnValues.add(strs);
                }
            }

            wb = ExportExcel.getHssfWorkBook(wb, "安技改信息", cloumnNames1, cloumnValues);

            try {
                response.setHeader("content-disposition",
                        "attachment;filename=" + URLEncoder.encode("铀尾矿（渣）库信息", "utf-8") + ".xls");
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
}