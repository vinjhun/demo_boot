package com.example.Proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * The CGLib 
 * 
 * **/
public class CGLibProxy implements MethodInterceptor
{
    public Object thisObject;
    
    public Object createProxyObject(Object thisObject) {
      this.thisObject = thisObject;
      
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(thisObject.getClass().getSuperclass());
      enhancer.setCallback(this);
      
      return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable
    {
        System.out.println("[ -- PRE INTERCEPTOR START --]");
        System.out.println(arg1.getName());
        System.out.println("[ -- PRE INTERCEPTOR END --]");
        
        Object ref = arg1.invoke(thisObject, arg2);
        
        
        System.out.println("[ -- POST INTERCEPTOR START --]");
        System.out.println(arg1.getName());
        System.out.println("[ -- POST INTERCEPTOR END --]");
        
        return ref;
    }


}
