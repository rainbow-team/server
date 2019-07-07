package com.rainbow.supervision.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/7 11:07
 * @Description:
 **/
public class BreakCheckExtend extends BreakChecker {
    private String checkMethodValue;

    private String checkLevelValue;

    public String getCheckMethodValue() {
        return checkMethodValue;
    }

    public void setCheckMethodValue(String checkMethodValue) {
        this.checkMethodValue = checkMethodValue;
    }

    public String getCheckLevelValue() {
        return checkLevelValue;
    }

    public void setCheckLevelValue(String checkLevelValue) {
        this.checkLevelValue = checkLevelValue;
    }
}
