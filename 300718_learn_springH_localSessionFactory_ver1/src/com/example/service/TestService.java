package com.example.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("testService")
@Configuration
@Transactional
public class TestService implements ITestService
{
    @Override
    public void updateTest()
    {
        // try here
        System.out.println("test update");

    }

    @Override
    public void findTest()
    {
        
        System.out.println("test find");
        
    }
}
