package com.example.bean_path;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanHelper implements ApplicationContextAware
{
    ApplicationContext ctx;
    
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException
    {
        if(ctx != null)
        {
            this.ctx = ctx;
        }
        
    }
    
    public <T> T getBean(Class<T> c) {
        return ctx.getBean(c);
    }

}
