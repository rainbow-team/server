package com.rainbow.common.config;

import com.rainbow.common.shiro.ShiroProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:liuhao
 * @Date:2019/4/29 09:46
 * @Description:
 **/
@Configuration
@ConfigurationProperties(prefix = "rainbow")
public class RainbowProperties {

    public ShiroProperties getShiro() {
        return shiro;
    }

    public void setShiro(ShiroProperties shiro) {
        this.shiro = shiro;
    }

    private ShiroProperties shiro = new ShiroProperties();

    private String timeFormat = "yyyy-MM-dd HH:mm:ss";

    private boolean openAOPLog = true;


    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isOpenAOPLog() {
        return openAOPLog;
    }

    public void setOpenAOPLog(boolean openAOPLog) {
        this.openAOPLog = openAOPLog;
    }
}
