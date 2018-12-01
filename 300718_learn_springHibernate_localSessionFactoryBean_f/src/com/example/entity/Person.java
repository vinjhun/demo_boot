package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="person")
@Table(name = "A_Testt")
public class Person
{
    private String module;
    private String url;

    @Id
    @Column(name="MODULE",nullable=false)
    public String getModule()
    {
        return module;
    }

    public void setModule(String module)
    {
        this.module = module;
    }

    @Column(name="URL",nullable=false)
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

}
