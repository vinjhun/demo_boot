package com.example.iObject;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

//must have id
@Component
public interface IFarmer
{
    public String findIdByRank(String id);
    
    public BigDecimal getSalaryPerHour(String rank);
    
    public String getTaskDescription(String id);
    
    
    public void testOne();
    
    public void testTwo();
}
