package com.example_1.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args)
    {
        //Standard way of construct object
//        Boy oldBoy = new Boy();
        
        //IOC (Inversion of control)
        //the construction of the object is done by the ctx
        //using reflection
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");   //if there is no arg, use applicationContext.xml
        Boy boy = (Boy)ctx.getBean("boy");
        boy.setAction("dancing");
        
        //DI (Dependency Injection)
        //using java bean property descriptor
        Girl girl = (Girl)ctx.getBean("girl");
        girl.kiss();
        System.out.println(girl.getCosmeticList());
        System.out.println(girl.getPerfumeList());
        
        //constructor-arg to pass the arg to the object
        //property
        //if using @Autowired, required AutowiredAnnotationBeanPostProcessor in beans.xml
    }
    
}
