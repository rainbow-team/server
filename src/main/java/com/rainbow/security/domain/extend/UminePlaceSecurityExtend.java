package com.rainbow.security.domain.extend;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.security.domain.UminePlaceSecurity;

/**
 * @Author:deepblue
 * @Date:2019/7/12 16:53
 * @Description:
 **/
public class UminePlaceSecurityExtend extends UminePlaceSecurity {

    // 铀矿冶单位的名称
    @BeanFieldAnnotation(order = 1)
    public String umineName;

    // 铀尾矿(渣)库的信息的值--设施名称
    @BeanFieldAnnotation(order = 2)
    public String uminePlaceName;

    // 铀尾矿(渣)库设施状态的值
    @BeanFieldAnnotation(order = 3)
    public String uminePlaceStatusTypeValue;

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

    public String getUmineName() {
        return umineName;
    }

    public void setUmineName(String umineName) {
        this.umineName = umineName;
    }

    public String getUminePlaceName() {
        return uminePlaceName;
    }

    public void setUminePlaceName(String uminePlaceName) {
        this.uminePlaceName = uminePlaceName;
    }

    public String getUminePlaceStatusTypeValue() {
        return uminePlaceStatusTypeValue;
    }

    public void setUminePlaceStatusTypeValue(String uminePlaceStatusTypeValue) {
        this.uminePlaceStatusTypeValue = uminePlaceStatusTypeValue;
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
