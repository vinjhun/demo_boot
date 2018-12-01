package com.example.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
   /**
    * Relationship between
    * 
    * jdbc: By using jdbc(java database connectivity) API, we able to communicate with different type of db
    * jpa:
    * template:
    * hibernate:
    * 
    * **/
    public static void main(String[] args)
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
    }
}
