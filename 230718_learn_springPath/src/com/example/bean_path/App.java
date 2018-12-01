package com.example.bean_path;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.ServiceTest;

public class App
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        SpringBeanHelper helper = new SpringBeanHelper();
        helper.setApplicationContext(ctx);
        
        ServiceTest service = (ServiceTest) helper.getBean(ServiceTest.class);
        // if no name is given, the 1st capital name will always be lower case
        
        service.test();
        
    }
    
    //*getting driver using class.forName(jdbc driver)
    //*and then use DriverManager.getConnection
    //*the url must be taken care of
    private void test() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sport";
        String user = "root";
        String password = "";
        
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            
            Statement stmt = conn.createStatement();
            
            //executeQuery
            //executeUpdate
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
