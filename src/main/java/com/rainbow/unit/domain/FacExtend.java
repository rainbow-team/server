package com.rainbow.unit.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/7 16:34
 * @Description:
 **/
public class FacExtend extends Fac {
    //营运单位信息
    private String serviceDepart;

    //监管类别信息
    private String supervisionCategoryValue;

    //设施类型信息
    private String typeValue;

    //设施状态信息
    private String statusValue;

    //审评状态信息
    private String reviewStatusValue;

    //许可情况信息
    private String permitSituationValue;

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
}
