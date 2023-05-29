package com.multipledbcon.dbconfig;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.multipledbcon.db2.repository",entityManagerFactoryRef = "db2EntityManagerFactory",transactionManagerRef = "db2TransactionManager")
public class Db2Config {
	
	
	@Bean(name ="db2DataSource")
	@ConfigurationProperties(prefix = "db2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	
	@Bean(name="db2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder,@Qualifier("db2DataSource") DataSource dataSource){
		return builder.dataSource(dataSource).packages("com.multipledbcon.db2.model").persistenceUnit("db2").build();
	}
	
	
	@Bean(name = "db2TransactionManager")
	public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
