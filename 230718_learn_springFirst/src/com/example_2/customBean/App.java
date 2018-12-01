package com.example_2.customBean;


public class App
{
    public static void main(String[] args)
    {
        //IOC (Inversion of control)
        //the construction of the object is done by the ctx
        //using reflection
        CustomContext ctx = new CustomXmlApplicationContext("beans_custom.xml");
        Boy boy = (Boy)ctx.getBean("Boy");
        boy.setAction("dancing");
        
        //DI (Dependency Injection)
        //using java bean property descriptor
        Girl girl = (Girl)ctx.getBean("Girl");
        girl.kiss();
        
        
    }
}
