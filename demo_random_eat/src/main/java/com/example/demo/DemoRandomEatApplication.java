package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;


@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class DemoRandomEatApplication {
	
	public static Logger log = LoggerFactory.getLogger(DemoRandomEatApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoRandomEatApplication.class, args);
		
		for(String name : ctx.getBeanDefinitionNames())
		{
			log.debug(name);
		}
		
	}
}
