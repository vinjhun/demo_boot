package com.example.features;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Security extends ASecurity
{
    @Before(value="salaryPointCut()")
    public void checkBeforeSecurity(JoinPoint jp) {
        System.out.println("-- BEFORE --");
    }
    
    @AfterReturning(value="salaryPointCut()",returning="returning")
    public void checkAfterReturningSecurity(JoinPoint jp,Object returning) {
        System.out.println("    -- AFTER_RETURNING --");
        
    }
    
    @AfterThrowing(value="salaryPointCut()",throwing="throwing")
    public void checkAfterThrowingSecurity(JoinPoint jp,Throwable throwing) {
        System.out.println("    --  AFTER_TRHOWING --");
        
    }
    
    @After(value="salaryPointCut()")
    public void checkAfterSecurity(JoinPoint jp) {
        System.out.println("--  AFTER --");
        
    }
    
    @Around(value="salaryPointCut()")
    public Object checkAroundSecurity(ProceedingJoinPoint pjp) {
        
        Object obj = null;
        
        System.out.println("    --  AROUND_BEFORE --");
        
        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
            
        System.out.println("    --  AROUND_AFTER --");
        
        return obj;
        
    }
    
//    @Before(value="execution (* com.example.main.Farmer.testOne(..))")
    public void testAspect()
    {
        System.out.println("Aspect is used");
    }
}
