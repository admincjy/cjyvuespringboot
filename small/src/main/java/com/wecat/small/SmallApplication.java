package com.wecat.small;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@ServletComponentScan
@SpringBootApplication
//定时任务
@EnableScheduling  
@EnableCaching
@MapperScan("com.wecat.small") 
public class SmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmallApplication.class, args);
		System.out.println("启动成功");
	}
}
