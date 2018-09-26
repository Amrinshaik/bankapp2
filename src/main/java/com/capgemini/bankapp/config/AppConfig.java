package com.capgemini.bankapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages= {"com.capgemini.bankapp"})
@PropertySource(value="classpath:db.properties")
public class AppConfig {
	
/*1  @Bean
	public DataSource getDataSource() {
	DriverManagerDataSource dataSource=new DriverManagerDataSource();
	dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost:3306/bankappdata");         //hardcoding the values requires modification in future which inturn requires 
	dataSource.setUsername("root");                                       //recompilation and reloading. to avoid this we use either@Value or environment variable method
	dataSource.setPassword("root");
		return dataSource;	
	}
	//datasource object injected into jdbc
*/
	
	
	/* 2  @Autowired
	private Environment environment;*/
	
	@Value("${db.driverClassName}")
	private String driverClassName;
	@Value("${db.url}")
	private String dburl;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		/* 2   dataSource.setDriverClassName(environment.getProperty("db.driverClassName"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));*/
		
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dburl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}



@Bean
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate =new JdbcTemplate(getDataSource());
		//jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
	}
//jdbc object injected into repository by container

}
