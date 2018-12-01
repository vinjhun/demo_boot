package com.example.main;

import java.math.BigDecimal;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.example.general.Human;
import com.example.iObject.IFarmer;

@Component
@Configuration
@EnableAspectJAutoProxy
public class Farmer extends Human implements IFarmer
{
    public String findName(String id)
    {
        String random = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        
        while(builder.length() <= 20)
        {
            Integer currentLength = ((int)Math.random() * random.length());
            
            builder.append(random.charAt(currentLength));
        }
        
        return "[ Name is : " + builder.toString() + " ] ";
        
    }
    
    public String findIdByRank(String id)
    {
        Integer uB = 6;
        Integer lB = 1;
        
        Integer random = lB + (int) (Math.random() * ((uB-lB)+1));
        
        return "[ Rank is  " + random + " ]";
    }

    public BigDecimal getSalaryPerHour(String rank)
    {
        System.out.println("*****RUN_SALARY_METHOD*****");
        Integer uB = 4000;
        Integer lB = 2500;
        
        Integer random = lB + (int) (Math.random() * ((uB-lB)+1));
        
        return new BigDecimal(random);
    }

    public String getTaskDescription(String id)
    {
        return "[Task Description Under Construction]";
    }

    @Override
    public void testOne()
    {
        System.out.println("test one running");
    }

    @Override
    public void testTwo()
    {
        System.out.println("test two running");
        testOne();
    }
}
