package com.rainbow.supervision.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;

import java.util.Date;

/**
 * @Author:deepblue
 * @Date:2019/7/7 11:43
 * @Description:
 **/
public class SupervisionTrainRecordExtend extends SupervisorTrainRecord {

    @BeanFieldAnnotation(order = 2)
    public String trainClass;
    public Date trainStartDate;
    public Date trainEndDate;
    /**
     * 身份证号
     */
    @BeanFieldAnnotation(order = 1)
    public String identity;

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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
