package com.example.main;

import com.example.Proxy.CGLibProxy;
import com.example.object.Human;

public class App
{
    public static void main(String[] args) throws IllegalArgumentException, InstantiationException, IllegalAccessException
    {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        
        //create a farm from jdkproxy
        
        //The proxy that are created is only for interface
//        IFarmer farmer = (IFarmer)jdkProxy.createProxyObject(Farmer.class.newInstance());
//        System.out.println("CUT POINT");
//        farmer.task();
//        farmer.salaryEarn();
        
        CGLibProxy proxy = new CGLibProxy();
        
        Farmer farmer = (Farmer)proxy.createProxyObject(Farmer.class.newInstance());
//        System.out.println(farmer.name("Albeham"));
//        System.out.println(farmer.age(19));
        System.out.println(farmer.farmerOwnMethod1());
    }
}
