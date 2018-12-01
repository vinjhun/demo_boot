package com.example.config;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages= {"com.example.aspect"})
public class FarmConfig implements IFarm
{

    @Override
    public String getFarmName()
    {
        final String randomNameList = "ABCDEFGHIJKLMNOPQRSTUVWXTZ";
        StringBuilder builder = new StringBuilder();
        
        while(builder.length() <= 10)
        {
            Integer test = 0 + (int)(Math.random() * ((20 - 0) + 1));
            
            builder.append(randomNameList.charAt(test));
        }
        
        return builder.toString();
    }

    @Override
    public BigDecimal getDailyProfit()
    {
        BigDecimal upB = new BigDecimal("9999999.99");
        BigDecimal lowB = new BigDecimal("10000.00");
        
        return (upB.subtract(lowB)).multiply(new BigDecimal(Math.random()), MathContext.DECIMAL128).add(lowB).setScale(2,RoundingMode.HALF_UP);
    }

}
