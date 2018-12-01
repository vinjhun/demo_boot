package com.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FarmSecurity
{
    @Before(value="execution (* com.example.config.FarmConfig.getDailyProfit(..))")
    public void checkFarmAuthorizer() {
        System.out.println(" -- Check Farm Authorizer --"); 
    }
    
}
