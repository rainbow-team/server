package com.rainbow.dataMigration.service.impl;

import com.rainbow.attachment.dao.FileInfoMapper;
import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.check.dao.FacCheckMapper;
import com.rainbow.check.dao.FacFileCheckMapper;
import com.rainbow.check.domain.FacCheck;
import com.rainbow.check.domain.FacFileCheck;
import com.rainbow.common.util.ExportExcel;
import com.rainbow.dataMigration.service.DataMigrationService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 13260 on 2019/8/27.
 */
@Service("DataMigrationService")
public class DataMigrationServiceImpl implements DataMigrationService {

    @Autowired
    FacCheckMapper facCheckMapper;

    @Autowired
    FacFileCheckMapper facFileCheckMapper;

    @Autowired
    FileInfoMapper fileInfoMapper;

    @Override
    public  void exportData(String type,HttpServletResponse response){

        String[] tables = type.split(",");

        List<String[]> cloumnValues = new ArrayList<>();

        if(tables!=null&&tables.length>0){

            for ( String str:tables) {
                switch (str) {
                    //核设施审评
                    case "1":

                        List<FacCheck> facCheckList = facCheckMapper.selectAll();
                        if(facCheckList!=null&&facCheckList.size()>0){

                            for (FacCheck facCheck:facCheckList) {
                                String[] strs = new String[]{
                                        facCheck.getServiceId(),
                                        facCheck.getFacId(),
                                        facCheck.getTypeId(),
                                        facCheck.getStageId(),
                                        DateToString(facCheck.getCheckDate()),
                                        facCheck.getNote(),
                                        facCheck.getIsImport().toString(),
                                        facCheck.getCreatorId(),
                                        DateToString(facCheck.getCreateDate()),
                                        facCheck.getModifyId(),
                                        DateToString(facCheck.getModifyDate())
                                };
                                cloumnValues.add(strs);
                            }
                        }
                        String[] cloumnNames=new String[]{"id",
                                "service_id",
                                "fac_id",
                                "type_id",
                                "stage_id",
                                "check_date",
                                "note",
                                "is_import",
                                "creator_id",
                                "create_date",
                                "modify_id",
                                "modify_date"
                        };
                        HSSFWorkbook wb = new HSSFWorkbook();
                        wb = ExportExcel.getHssfWorkBook(wb,"核设施审评",cloumnNames,cloumnValues);

                        List<FacFileCheck> facFileCheckList = facFileCheckMapper.selectAll();

                        cloumnValues = new ArrayList<>();

                        if(facFileCheckList!=null&&facFileCheckList.size()>0){

                            for (FacFileCheck facFileCheck:facFileCheckList) {
                                String[] strs = new String[]{
                                        facFileCheck.getCheckFacId(),
                                        facFileCheck.getFileName(),
                                        facFileCheck.getFacCheckFileTypeId(),
                                        DateToString(facFileCheck.getFileDate()),
                                        facFileCheck.getFileNo(),
                                        facFileCheck.getIsImport().toString()
                                };
                                cloumnValues.add(strs);
                            }
                        }

                        String[] cloumnNamesFacFile=new String[]{"id",
                                "check_fac_id",
                                "file_name",
                                "fac_check_file_type_id",
                                "file_date",
                                "file_no",
                                "is_import"
                        };

                        wb = ExportExcel.getHssfWorkBook(wb,"核设施审评阶段文件表",cloumnNamesFacFile,cloumnValues);


                        //附件
                        List<FileInfo> fileInfoList =fileInfoMapper.getFileInfoByTableName("check_fac_file");


                        break;
                    //日常监督信息
                    case "2":
                        break;
                    //监督检查信息
                    case "3":
                        break;
                    //监督见证信息
                    case "4":
                        break;
                    //核设施安全问题
                    case "5":
                        break;
                }
            }
        }
    }

    public String DateToString(Date data){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(data);

        return  dateString;
    }

}
