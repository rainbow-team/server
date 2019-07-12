package com.rainbow.supervision.domain.extend;

import com.rainbow.supervision.domain.SupervisionExpert;

import javax.persistence.*;
import java.util.Date;


public class SupervisionExpertExtend extends SupervisionExpert {

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