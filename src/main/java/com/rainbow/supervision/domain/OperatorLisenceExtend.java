package com.rainbow.supervision.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/7 12:26
 * @Description:
 **/
public class OperatorLisenceExtend extends OperatorLisence {
    private String licenseTypeValue;

    public String getLicenseTypeValue() {
        return licenseTypeValue;
    }

    public void setLicenseTypeValue(String licenseTypeValue) {
        this.licenseTypeValue = licenseTypeValue;
    }
}
