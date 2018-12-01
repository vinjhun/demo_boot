package com.example.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler
{

    Object thisObject;

    public Object createNewProxyObject(Object t)
    {
        this.thisObject = t;

        return Proxy.newProxyInstance(this.thisObject.getClass().getClassLoader(),
                this.thisObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object object = method.invoke(proxy, args);
        return object;
    }

}
