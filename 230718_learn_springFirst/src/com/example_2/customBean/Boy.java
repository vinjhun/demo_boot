package com.example_2.customBean;

public class Boy
{
    private String action;

    public void setAction(String action)
    {
        this.action = action;
    }
    
    public void start() {
        System.out.println("Boy is performing : " + action);
    }

}
