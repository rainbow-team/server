package com.rainbow;

import com.rainbow.common.config.RainbowProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties({RainbowProperties.class})
@MapperScan("com.rainbow.*.dao")
@EnableAsync
public class RainbowApplication {

	public static void main(String[] args) {
		SpringApplication.run(RainbowApplication.class, args);
	}

}
