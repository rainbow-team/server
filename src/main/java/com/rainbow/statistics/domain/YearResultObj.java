package com.rainbow.statistics.domain;

/**
 * @Author:deepblue
 * @Date:2019/7/24 13:13
 * @Description:
 **/
public class YearResultObj {

    //大于2000年的数量
    private Integer big;

    //小于2000年的数量
    private Integer small;

    private String typeName;

    public Integer getBig() {
        return big;
    }

    public void setBig(Integer big) {
        this.big = big;
    }

    public Integer getSmall() {
        return small;
    }

    public void setSmall(Integer small) {
        this.small = small;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
