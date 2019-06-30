package com.rainbow.common.domain;

import java.io.Serializable;

/**
 * Created by 13260 on 2019/6/30.
 */
public class Condition implements Serializable {

    private String Key;

    private String Value;

    public Condition() {
        super();
    }

    public Condition(String key, String value) {
        super();
        Key = key;
        Value = value;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
