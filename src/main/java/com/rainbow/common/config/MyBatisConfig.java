package com.rainbow.common.config;

import com.rainbow.common.interceptor.SqlStatementInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:deepblue
 * @Date:2019/4/30 10:45
 * @Description:
 **/
@Configuration
public class MyBatisConfig {

    /**
     * 配置 sql打印拦截器
     * application.yml中 showsql: true 时生效
     *
     * @return SqlStatementInterceptor
     */
    @Bean
    @ConditionalOnProperty(name = "rainbow.showsql", havingValue = "true")
    SqlStatementInterceptor sqlStatementInterceptor() {
        return new SqlStatementInterceptor();
    }
}
