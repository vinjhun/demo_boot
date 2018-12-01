package com.example.features;

import org.aspectj.lang.annotation.Pointcut;

public abstract class ASecurity
{
    @Pointcut(value="execution (* com.example.main.Farmer.getSalaryPerHour(..))")
    protected void salaryPointCut(){}
    
    @Pointcut(value="execution (* com.example.main.Farmer.findIdByRank(..))")
    protected void rankPointCut(){}
    
    @Pointcut(value="execution (* com.example.main.Farmer.findName(..))")
    protected void namePointCut(){}
    
    @Pointcut(value="execution (* com.example.main.Farmer.getTaskDescription(..))")
    protected void taskDescriptionPointCut(){}
    
  //new type of expression? or the tutorial is too old..
  // or pointcut require within..  
    @Pointcut(value="within(com.example.main.*)")   
    protected void newSalaryPointCut(){}
    
    
}
