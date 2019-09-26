package com.rainbow.unit.domain;

import com.rainbow.common.annotation.BeanFieldAnnotation;

/**
 * @Author:deepblue
 * @Date:2019/7/7 19:31
 * @Description:
 **/
public class UmineplaceExtend extends Umineplace {

    // 铀矿冶单位信息
    @BeanFieldAnnotation(order = 3)
    public String umineName;

    // 铀尾矿(渣)库等级值
    @BeanFieldAnnotation(order = 5)
    public String levelValue;

    // 设施状态的值
    @BeanFieldAnnotation(order = 6)
    public String statusValue;

    // 审评状态的值
    @BeanFieldAnnotation(order = 7)
    public String reviewStatus;

    // 许可情况的值
    @BeanFieldAnnotation(order = 8)
    public String permitSituationValue;

    public String getUmineName() {
        return umineName;
    }

    public void setUmineName(String umineName) {
        this.umineName = umineName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getPermitSituationValue() {
        return permitSituationValue;
    }

    public void setPermitSituationValue(String permitSituationValue) {
        this.permitSituationValue = permitSituationValue;
    }
}
