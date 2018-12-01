package com.example.threadTest;

import java.util.concurrent.atomic.AtomicInteger;

public class App
{
    private static final AtomicInteger uniqueId = new AtomicInteger(0);

    public static ThreadLocal<Integer> uniqueNum  = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return uniqueId.getAndIncrement();
        }
    };
    
    public static void main(String[] args)
    {
        for(int i = 0; i < 5; i ++)
        {
            new Thread(new Runnable() {
                
                @Override
                public void run()
                {
                    System.out.println("Thread-"+ Thread.currentThread().getName() + ": "
                            + uniqueNum.get());
                }
            },new Integer(i).toString()).start();
            
            
            new Thread(new Runnable() {
                
                @Override
                public void run()
                {
                    System.out.println("Thread-"+ Thread.currentThread().getName() + ": "
                            + uniqueNum.get());
                }
            },new Integer(i).toString()).start();
        }
            
    }
}
