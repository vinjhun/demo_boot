package com.example.junit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.dao.ITestDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class App
{
    public static void main(String[] args) throws IOException 
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        
        //try test c3p0 
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            
            ComboPooledDataSource ds = (ComboPooledDataSource)ac.getBean("dataSource");
            Connection conn = ds.getConnection();
            
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //try test session factory
//        SessionFactoryImpl sessionFactoryBean = (SessionFactoryImpl) ac.getBean("sessionFactoryBean");
//        System.out.println(sessionFactoryBean);
        
        
//        TestService testService = (TestService)ac.getBean("testService");
//        testService.findTest();
        
        //if using this one, 'sessionFactory 'hibernateTemplate' is required
        ITestDao dao = (ITestDao)ac.getBean("testDao");
        dao.findTest();
    }
}
