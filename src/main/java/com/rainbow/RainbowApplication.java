package com.rainbow;

import com.rainbow.common.config.RainbowProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties({RainbowProperties.class})
@MapperScan("com.rainbow.*.dao")
@EnableAsync
@EnableCaching
public class RainbowApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RainbowApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RainbowApplication.class, args);
	}

}
