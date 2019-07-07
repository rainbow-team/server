package com.rainbow.supervision.domain;

import javax.persistence.*;
import java.util.Date;


public class SupervisionExpertExtend extends SupervisionExpert {

    /**
     *职称的值
     */
    private String titleValue;

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue;
    }





}