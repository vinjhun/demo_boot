package com.example.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class TestDao extends HibernateDaoSupport implements ITestDao
{
    public void findTest() throws IOException
    {
        HibernateTemplate ht = getHibernateTemplate();
        String queryString = "select a from Test a";
        
        List<?> testResult =  ht.find(queryString);
        
        System.out.println(testResult);
    }
}
