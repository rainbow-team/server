package com.rainbow.supervision.domain.extend;

import com.rainbow.supervision.domain.Supervisor;

/**
 * @Author:deepblue
 * @Date:2019/8/1 16:57
 * @Description:
 **/
public class SupervisorExtend extends Supervisor {

    //核安全授权监管机构名称
    private String orgName;

    //监督员类别名称
    private String typeValue;

    //职称名称
    private String titleName;

    //政治面貌名称
    private String politicalValue;

    //学历名称
    private String educationValue;

    //学位名称
    private String degreeValue;


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
}
