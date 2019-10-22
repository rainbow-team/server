package com.rainbow.common.util;

import com.rainbow.common.config.RainbowProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by 13260 on 2019/10/22.
 */

@Component
public class MyApplicationRunner  implements ApplicationRunner {

    @Autowired
    RainbowProperties rainbowProperties;

    @Override
    public void run(ApplicationArguments var1) throws Exception{

        office2PDF.startCommand(rainbowProperties.getOpenoffice());
    }
}
