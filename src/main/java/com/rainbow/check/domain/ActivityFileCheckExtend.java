package com.rainbow.check.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/10 16:56
 * @Description:
 **/
public class ActivityFileCheckExtend extends ActivityFileCheck {

    //核活动及其他审评信息文件类型的值
    private String activityCheckFileTypeValue;

    public String getActivityCheckFileTypeValue() {
        return activityCheckFileTypeValue;
    }

    public void setActivityCheckFileTypeValue(String activityCheckFileTypeValue) {
        this.activityCheckFileTypeValue = activityCheckFileTypeValue;
    }
}
