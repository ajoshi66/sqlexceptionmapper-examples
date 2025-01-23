package com.joshi.manufacturer.config;

import org.joshi.sqlexceptionmapper.GenericSqlExceptionHandler;
import org.joshi.sqlexceptionmapper.SqlExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManufacturerConfig {
	
	@Value("${sqlexceptionmapper.configfile.h2db}")
	private String h2dbConfigFile;
	
	@Bean
	public SqlExceptionHandler h2dbHandler() {
		return new GenericSqlExceptionHandler(SqlExceptionHandler.DBTYPE_H2DB, h2dbConfigFile);
	}

}
