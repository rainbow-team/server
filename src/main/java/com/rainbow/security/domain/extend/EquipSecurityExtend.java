package com.rainbow.security.domain.extend;

import com.rainbow.security.domain.EquipSecurity;

/**
 * @Author:deepblue
 * @Date:2019/7/12 15:48
 * @Description:
 **/
public class EquipSecurityExtend extends EquipSecurity {

    //核设备单位的名称
    private String equipDepartName;

    //核设施营运单位名称
    private String serviceDepartName;

    //核设施信息名称
    private String facName;

    //安全问题检查类型的值
    private String checkTypeValue;

    //核设施安全问题类别的值
    private String questionTypeValue;

    //安全问题整改状态的值
    private String reformStatusTypeValue;

    public String getEquipDepartName() {
        return equipDepartName;
    }

    public void setEquipDepartName(String equipDepartName) {
        this.equipDepartName = equipDepartName;
    }

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

    public String getReformStatusTypeValue() {
        return reformStatusTypeValue;
    }

    public void setReformStatusTypeValue(String reformStatusTypeValue) {
        this.reformStatusTypeValue = reformStatusTypeValue;
    }
}
