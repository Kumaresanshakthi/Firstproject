package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Authorities;
import com.niit.model.BillingAddress;
import com.niit.model.CartItem;
import com.niit.model.Category;
import com.niit.model.Customer;
import com.niit.model.CustomerOrder;
import com.niit.model.Product;
import com.niit.model.ShippingAddress;
import com.niit.model.Supplier;
import com.niit.model.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DBConfig {
@Bean
public LocalSessionFactoryBean sessionFactory()
{
	 LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
     sessionFactory.setDataSource(dataSource());
     sessionFactory.setPackagesToScan(new String[] { "com.niit.model" });
     sessionFactory.setHibernateProperties(hibernateProperties());
     sessionFactory.setAnnotatedClasses(Category.class,Product.class,Customer.class,CustomerOrder.class,Supplier.class,User.class,ShippingAddress.class,Authorities.class,BillingAddress.class,CartItem.class);
     System.out.println("SessionCreated");
     return sessionFactory;	
}
@Bean
public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:tcp://localhost/~/Shakthikumar");
    dataSource.setUsername("kumar");
    dataSource.setPassword("kumar");
	return dataSource;
}
private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.show_sql","true");
	properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	properties.setProperty("hibernate.hbm2ddl.auto","update");
	
    return properties;        
}
@Bean
@Autowired
public HibernateTransactionManager transactionManager(SessionFactory s) {
   HibernateTransactionManager txManager = new HibernateTransactionManager();
   txManager.setSessionFactory(s);
   return txManager;
}}