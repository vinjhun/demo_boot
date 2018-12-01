package com.example.main;

import java.math.BigDecimal;

import com.example.iobject.IFarmer;
import com.example.object.Human;

public class Farmer extends Human implements IFarmer
{
    @Override
    public String name(String name)
    {
        return ("Farmer name is  " + name);
    }
    
    @Override
    public Integer age(Integer age)
    {
        return age;
    }
    
    @Override
    public String task()
    {
        System.out.println("Farmer task : ");
        return null;
    }

    @Override
    public BigDecimal salaryEarn()
    {
        System.out.println("Farmer salary : ");
        return null;
    }
    
    public String farmerOwnMethod1() 
    {
        return "Testing farmer own method";
    }
}
