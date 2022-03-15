package com.mab.merchantapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@EnableAspectJAutoProxy(proxyTargetClass=true)  
@SpringBootApplication
public class IntercomApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntercomApplication.class, args);
	}

}
