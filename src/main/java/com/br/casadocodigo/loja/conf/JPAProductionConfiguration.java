package com.br.casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("prod")
public class JPAProductionConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public Properties additionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}
	
	@Bean
	public DataSource dataSource() throws URISyntaxException{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		
		//usuario:senha@host:port/path
		URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));

		//dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setUrl("jdbc:postgresql://"+dbUrl.getHost()+":"+dbUrl.getPort()+dbUrl.getPath());	
		//dataSource.setUsername("root");
		//dataSource.setPassword("");
	    dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
	    dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);

		return dataSource;
	}


}
