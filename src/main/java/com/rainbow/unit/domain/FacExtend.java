package com.rainbow.unit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;

/**
 * @Author:deepblue
 * @Date:2019/7/7 16:34
 * @Description:
 **/
public class FacExtend extends Fac {
    // 营运单位信息
    @BeanFieldAnnotation(order = 4)
    public String serviceDepart;

    // 监管类别信息
    @BeanFieldAnnotation(order = 7)
    public String supervisionCategoryValue;

    // 设施类型信息
    @BeanFieldAnnotation(order = 8)
    public String typeValue;

    // 设施状态信息
    @BeanFieldAnnotation(order = 9)
    public String statusValue;

    // 审评状态信息
    @BeanFieldAnnotation(order = 10)
    public String reviewStatusValue;

    // 许可情况信息
    @BeanFieldAnnotation(order = 11)
    public String permitSituationValue;

    @BeanFieldAnnotation(order = 14)
    public String isEarthquakeString;

    @BeanFieldAnnotation(order = 15)
    public String isFloodString;

    public String getServiceDepart() {
        return serviceDepart;
    }

    public void setServiceDepart(String serviceDepart) {
        this.serviceDepart = serviceDepart;
    }

    public String getSupervisionCategoryValue() {
        return supervisionCategoryValue;
    }

    public void setSupervisionCategoryValue(String supervisionCategoryValue) {
        this.supervisionCategoryValue = supervisionCategoryValue;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getReviewStatusValue() {
        return reviewStatusValue;
    }

    public void setReviewStatusValue(String reviewStatusValue) {
        this.reviewStatusValue = reviewStatusValue;
    }

    public String getPermitSituationValue() {
        return permitSituationValue;
    }

    public void setPermitSituationValue(String permitSituationValue) {
        this.permitSituationValue = permitSituationValue;
    }

    public String getIsEarthquakeString() {
        return isEarthquakeString;
    }

    public void setIsEarthquakeString(String isEarthquakeString) {
        this.isEarthquakeString = isEarthquakeString;
    }

    public String getIsFloodString() {
        return isFloodString;
    }

    public void setIsFloodString(String isFloodString) {
        this.isFloodString = isFloodString;
    }
}
