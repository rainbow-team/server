package com.rainbow.supervision.domain.extend;

import com.rainbow.config.domain.SystemConfig;
import com.rainbow.supervision.domain.Org;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:deepblue
 * @Date:2019/8/1 14:04
 * @Description:
 **/
public class OrgExtend extends Org {


    private String natureValue;

    public String getNatureValue() {
        String temp = "";

        if (this.getNature() != null && this.getNature().size() > 0) {
            for (SystemConfig value : this.getNature()) {
                if (temp != "") {
                    temp += "/";
                }
                temp += value.getValue();
            }
        }
        return temp;
    }

    private List<String> typeIds;

    public List<String> getTypeIds() {
        List<String> temp=new ArrayList<String>();

        if (this.getNature() != null && this.getNature().size() > 0) {
            for (SystemConfig value : this.getNature()) {
               temp.add(value.getId());
            }
        }
        return temp;
    }

    public void setTypeIds(List<String> typeIds) {
        this.typeIds = typeIds;
    }
}
