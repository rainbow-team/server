package com.rainbow.dataMigration.service;

import com.rainbow.common.domain.ResponseBo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 13260 on 2019/8/27.
 */
public interface DataMigrationService {

   void exportData(String type,HttpServletResponse response);

   ResponseBo importData(HttpServletRequest request);
}
