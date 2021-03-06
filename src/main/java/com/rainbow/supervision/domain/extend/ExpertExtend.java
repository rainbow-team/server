package com.rainbow.supervision.domain.extend;

import com.rainbow.common.util.IDCardUtil;
import com.rainbow.supervision.domain.Expert;


public class ExpertExtend extends Expert {

    /**
     *职称的值
     */
    private String titleValue;

    //年龄
    private Integer age;

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue;
    }

    public Integer getAge() {
        try {
            Integer result = IDCardUtil.getAge(this.getBirthday());
            return result;
        } catch (Exception e) {
            return 0;
        }
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}