package com.mab.merchantapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class TransactionConfig {
	
	@Bean(name = "chainedTransactionManager")
	@Primary
	public ChainedTransactionManager transactionManager(
			@Qualifier("uat_platformTransactionManager") PlatformTransactionManager uatTranx) {
		return new ChainedTransactionManager(uatTranx);
	}
}
