package com.mab.merchantapi.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsynConfig {
	    //ref: https://howtodoinjava.com/spring-boot2/rest/enableasync-async-controller/
		//ref: https://www.baeldung.com/spring-async#:~:text=Simply%20put%2C%20annotating%20a%20method,for%20async%20processing%20if%20necessary.
	 	@Bean(name = "asyncExecutor")
	    public Executor asyncExecutor() 
	    {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(3);
	        executor.setMaxPoolSize(50);
	        executor.setQueueCapacity(10000);
	        executor.setThreadNamePrefix("IntercomAsynchThread-");
	        executor.initialize();
	        return executor;
	    }
}
