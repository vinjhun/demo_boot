package com.example.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Remarks : -Writing a JdkProxy (Dynamic Proxy) requires an interfaces
 *           -Instances created can only access the interface method
 * **/
public class JdkProxy implements InvocationHandler
{
    public Object thisObject;
    
    public Object createProxyObject(Object thisObject) throws IllegalArgumentException, InstantiationException, IllegalAccessException {
        System.out.println("[PROXY CREATION]");
        
        this.thisObject = thisObject;
        
        /**
         *  1st arg : object class loader
         *  2nd arg : object interface // proxy required interface
         *  3rd arg : TMD refer to handler, THIS is the handler.. 
         * 
         * **/
        return Proxy.newProxyInstance(
                this.thisObject.getClass().getClassLoader(), 
                this.thisObject.getClass().getInterfaces(), 
                this);
    }
    
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println("--[PRE INVOKING]--");
        
        Object ref = method.invoke(thisObject, args);
        
        System.out.println("--[POST INVOKING]--");
        
        return ref;
    }

}
