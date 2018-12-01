package com.example.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.example.entity.Person;

/**
 * ,ApplicationContextAware
 * ***/

@Repository("personDao")
public class PersonDao implements IPersonDao
{
    @Test
    @Override
    public void findUser()
    {
        //try test
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        
        System.out.println("testing");
    }

    @Override
    public void saveUser()
    {
        
    }

}
