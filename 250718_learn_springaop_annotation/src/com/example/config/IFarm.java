package com.example.config;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public interface IFarm
{
    public String getFarmName();
    
    public BigDecimal getDailyProfit();
}
