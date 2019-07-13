package com.rainbow.supervision.domain.extend;

import com.rainbow.supervision.domain.Expert;


public class ExpertExtend extends Expert {

    /**
     *职称的值
     */
    private String titleValue;

    //年龄
    private String age;

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}