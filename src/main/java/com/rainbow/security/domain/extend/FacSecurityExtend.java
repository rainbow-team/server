package com.rainbow.security.domain.extend;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.security.domain.FacSecurity;

/**
 * @Author:deepblue
 * @Date:2019/7/12 11:28
 * @Description:
 **/
public class FacSecurityExtend extends FacSecurity {

    // 核设施营运单位名称
    @BeanFieldAnnotation(order = 1)
    public String serviceDepartName;

    // 核设施信息名称
    @BeanFieldAnnotation(order = 2)
    public String facName;

    // 核设施状态的值
    @BeanFieldAnnotation(order = 3)
    public String facStatusTypeValue;

    // 安全问题检查类型的值
    @BeanFieldAnnotation(order = 4)
    public String checkTypeValue;

    // 核设施安全问题类别的值
    @BeanFieldAnnotation(order = 7)
    public String questionTypeValue;

    // 核设施安全问题性质的值
    @BeanFieldAnnotation(order = 8)
    public String questionNatureValue;

    // 安全问题整改状态的值
    @BeanFieldAnnotation(order = 9)
    public String reformStatusTypeValue;

    public String getServiceDepartName() {
        return serviceDepartName;
    }

    public void setServiceDepartName(String serviceDepartName) {
        this.serviceDepartName = serviceDepartName;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getFacStatusTypeValue() {
        return facStatusTypeValue;
    }

    public void setFacStatusTypeValue(String facStatusTypeValue) {
        this.facStatusTypeValue = facStatusTypeValue;
    }

    public String getCheckTypeValue() {
        return checkTypeValue;
    }

    public void setCheckTypeValue(String checkTypeValue) {
        this.checkTypeValue = checkTypeValue;
    }

    public String getQuestionTypeValue() {
        return questionTypeValue;
    }

    public void setQuestionTypeValue(String questionTypeValue) {
        this.questionTypeValue = questionTypeValue;
    }

    public String getQuestionNatureValue() {
        return questionNatureValue;
    }

    public void setQuestionNatureValue(String questionNatureValue) {
        this.questionNatureValue = questionNatureValue;
    }

    public String getReformStatusTypeValue() {
        return reformStatusTypeValue;
    }

    public void setReformStatusTypeValue(String reformStatusTypeValue) {
        this.reformStatusTypeValue = reformStatusTypeValue;
    }
}
