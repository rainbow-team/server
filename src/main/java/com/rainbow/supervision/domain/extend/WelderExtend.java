package com.rainbow.supervision.domain.extend;

import com.rainbow.supervision.domain.Welder;

/**
 * @Author:deepblue
 * @Date:2019/7/7 11:07
 * @Description:
 **/
public class WelderExtend extends Welder {

    //焊接人员考试地点
    private String examPlaceValue;

    public String getExamPlaceValue() {
        return examPlaceValue;
    }

    public void setExamPlaceValue(String examPlaceValue) {
        this.examPlaceValue = examPlaceValue;
    }
}
