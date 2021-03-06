package com.rainbow.supervision.domain.extend;

import com.rainbow.supervision.domain.BreakChecker;

/**
 * @Author:deepblue
 * @Date:2019/7/7 11:07
 * @Description:
 **/
public class BreakCheckerExtend extends BreakChecker {

    //无损检验方法
    private String checkMethodValue;

    //无损检验级别
    private String checkLevelValue;

    //无损检验人員考試地點
    private String examPlaceValue;

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

    public String getExamPlaceValue() {
        return examPlaceValue;
    }

    public void setExamPlaceValue(String examPlaceValue) {
        this.examPlaceValue = examPlaceValue;
    }
}
