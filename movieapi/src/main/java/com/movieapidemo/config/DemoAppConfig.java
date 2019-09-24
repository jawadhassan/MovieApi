package com.movieapidemo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.movieapidemo")
@PropertySource({"classpath:persistence-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer{

	@Autowired
	private Environment env;
	
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	@Bean
	public DataSource dataSource() {
		
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		
		try {
			
			comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
			
		}catch(PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
		
		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
		
		logger.info("jdbc user:"+env.getProperty("jdbc.user"));
		logger.info("jdbc url:"+env.getProperty("jdbc.url"));
		
		
		//set database connections props
		comboPooledDataSource.setUser(env.getProperty("jdbc.user"));
		comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));
		
		
		comboPooledDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		comboPooledDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		comboPooledDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		comboPooledDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return comboPooledDataSource;
	}
	
	
	
	private Properties getProperties(){
		
		Properties properties = new Properties();
		
		properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		
		properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		return properties;
		
	}
	
	
	
	public int getIntProperty(String propName){
		
		String propVal = env.getProperty(propName);
		
		int IntPropVal = Integer.parseInt(propVal);
		
		return IntPropVal;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource());
		
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		
		sessionFactory.setHibernateProperties(getProperties());
		
		return sessionFactory;
	}
	
	
	@Bean
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		
		tx.setSessionFactory(sessionFactory);
		
		return tx;
	}
}
