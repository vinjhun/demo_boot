package com.example_1.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Girl
{
    @Qualifier
    private Boy boy;
    private String name;
    private String age;
    private List<String> cosmeticList;
    private List<String> perfumeList;

    public Girl(String name, String age) {
        this.name = name;
        this.age = age;
    }
    
    @Autowired
    @Qualifier("boy")
    public void setBoy(Boy boy)
    {
        this.boy = boy;
    }

    public List<String> getCosmeticList()
    {
        return cosmeticList;
    }

    public void setCosmeticList(List<String> cosmeticList)
    {
        this.cosmeticList = cosmeticList;
    }

    public List<String> getPerfumeList()
    {
        return perfumeList;
    }

    public void setPerfumeList(List<String> perfumeList)
    {
        this.perfumeList = perfumeList;
    }

    public void kiss()
    {
        boy.display();
        System.out.println("Girl kiss");
    }

}
