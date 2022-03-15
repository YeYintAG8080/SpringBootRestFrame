package com.mab.merchantapi.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource("classpath:database.properties")
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	
	private @Value("${spring.datasource.url}") String dbUrl;
	private @Value("${spring.datasource.username}") String dbUsername;
	private @Value("${spring.datasource.password}") String dbPassword;
	private @Value("${spring.datasource.driver-class-name}") String dbDriverclass;
	
	//@Autowired
	//private DatabaseProperties databaseProperties;
	
    //step 1
    @Bean(name = "uat_datasource")
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		   dataSource.setUrl(dbUrl);
	       dataSource.setUsername(dbUsername);
	       dataSource.setPassword(dbPassword);
	       dataSource.setDriverClassName(dbDriverclass);
	       Properties prop=new Properties();
	       prop.setProperty("useUnicode","true");
	       prop.setProperty("characterEncoding","utf8");
	       dataSource.setConnectionProperties(prop);
		return  dataSource;
    }

    //step 2
    @Bean(name = "uat_namedJdbcTemplate")
	public NamedParameterJdbcTemplate uat_namedJdbcTemplate(@Qualifier("uat_datasource") DataSource ds) {
	 return new NamedParameterJdbcTemplate(ds);
	}
	
    //step 2
	@Bean(name = "uat_jdbcTemplate")
	public JdbcTemplate uat_jdbcTemplate(@Qualifier("uat_datasource") DataSource ds) {
	 return new JdbcTemplate(ds);
	}

    //step 3
	@Bean(name="uat_platformTransactionManager")
	public PlatformTransactionManager txManager(){
	    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
	    return transactionManager;
	}
    
  
}
