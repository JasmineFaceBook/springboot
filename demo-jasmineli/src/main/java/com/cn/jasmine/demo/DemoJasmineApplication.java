package com.cn.jasmine.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 24166
 *
 */
@SpringBootApplication
//开启缓存
@EnableCaching
//开启异步
@EnableAsync
public class DemoJasmineApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoJasmineApplication.class, args);

	}
}
