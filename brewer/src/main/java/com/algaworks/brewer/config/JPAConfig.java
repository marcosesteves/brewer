package com.algaworks.brewer.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.Cervejas;

@Configuration
@EnableJpaRepositories(basePackageClasses = Cervejas.class)
//@ComponentScan(basePackageClasses = Cervejas.class)
public class JPAConfig {

	// Recuperando o Data Source definido em context.xml
	@Bean
	public DataSource dataSource() {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/brewerDB");
	}
	
	// Define o Hibernate e o adatador a ser utilizado pelo Hibernate para traduzir as instruções JPQL e Criteria  
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.POSTGRESQL);
		adapter.setShowSql(false); 		// 	Exibe ou não o SQL
		adapter.setGenerateDdl(false);  // Gera ou não o DDL do BD
		adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");	// Responsável por traduzir o JPQL ou Criteria para o sql do Postgresql
		return adapter;
}
	
	// Recupera o EntityManagerFactory com os objetos criados acima 
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter)  { // Passa o dataSource e o JpaVendorAdatper 
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
//		factory.setPackagesToScan("com.algaworks.brewer.model");  Define onde estão as entidades e requer uma String
		factory.setPackagesToScan(Cerveja.class.getPackage().getName());  // Desta forma se mudar o pacote no projeto ainda funcionará
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	
	// Para configurar as transações de BD
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
}
}