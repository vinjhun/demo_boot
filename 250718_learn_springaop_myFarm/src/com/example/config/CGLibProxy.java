package com.example.config;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGLibProxy implements MethodInterceptor
{
    Object thisObject;
    
    public Object createNewProxyObject(Object obj) {
        Enhancer enhancer = new Enhancer();
        
        enhancer.setSuperclass(obj.getClass().getSuperclass());
        enhancer.setCallback(this);
        
        return enhancer.create();
        
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable
    {
        Object object = arg1.invoke(arg0, arg3);
        return object;
    }
    

}
