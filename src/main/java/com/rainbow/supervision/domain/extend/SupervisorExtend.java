package com.rainbow.supervision.domain.extend;

import com.rainbow.common.annotation.BeanFieldAnnotation;
import com.rainbow.supervision.domain.Supervisor;

import java.util.Date;

/**
 * @Author:deepblue
 * @Date:2019/8/1 16:57
 * @Description:
 **/
public class SupervisorExtend extends Supervisor {

    //核安全授权监管机构名称
    @BeanFieldAnnotation(order = 4)
    public String orgName;

    //监督员类别名称
    @BeanFieldAnnotation(order = 5)
    public String typeValue;

    //职称名称
    @BeanFieldAnnotation(order = 7)
    public String titleName;

    //政治面貌名称
    @BeanFieldAnnotation(order = 9)
    public String politicalValue;

    //学历名称
    @BeanFieldAnnotation(order = 13)
    public String educationValue;

    //学位名称
    @BeanFieldAnnotation(order = 14)
    public String degreeValue;

    public Integer isExpire;

    @BeanFieldAnnotation(order = 10)
    public String sexName;

    public Integer getIsExpire() {
        if (this.getExpireDate() != null) {
            Date now = new Date();

            if (this.getExpireDate().getTime() > now.getTime()) {
                return 0;
            }
        }
        return 1;

    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getPoliticalValue() {
        return politicalValue;
    }

    public void setPoliticalValue(String politicalValue) {
        this.politicalValue = politicalValue;
    }

    public String getEducationValue() {
        return educationValue;
    }

    public void setEducationValue(String educationValue) {
        this.educationValue = educationValue;
    }

    public String getDegreeValue() {
        return degreeValue;
    }

    public void setDegreeValue(String degreeValue) {
        this.degreeValue = degreeValue;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }
}
