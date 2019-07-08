package com.rainbow.supervision.domain;

import java.util.Date;

/**
 * @Author:deepblue
 * @Date:2019/7/7 11:43
 * @Description:
 **/
public class SupervisionTrainRecordExtend extends SupervisionTrainRecord {
    private String trainClass;
    private Date trainStartDate;
    private Date trainEndDate;

    public String getTrainClass() {
        return trainClass;
    }

    public void setTrainClass(String trainClass) {
        this.trainClass = trainClass;
    }

    public Date getTrainStartDate() {
        return trainStartDate;
    }

    public void setTrainStartDate(Date trainStartDate) {
        this.trainStartDate = trainStartDate;
    }

    public Date getTrainEndDate() {
        return trainEndDate;
    }

    public void setTrainEndDate(Date trainEndDate) {
        this.trainEndDate = trainEndDate;
    }
}