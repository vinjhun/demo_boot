package com.example.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.config.FarmConfig;

public class App
{
    public static void main(String[] args)
    {
        //
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(FarmConfig.class);
        
        FarmConfig config = (FarmConfig)ctx.getBean("farmConfig");
        System.out.println("[ Farm Name : " + config.getFarmName() + " || Farm Profit :  " + config.getDailyProfit() + " ]");
        
        ////// TEST thread local  /////////////
        
//        for(int i = 0; i< 5; i++)
//        {
//            new Thread(new Runnable() {
//                
//                @Override
//                public void run()
//                {
//                    ThreadLocal<FarmConfig> randomConfig = new ThreadLocal<FarmConfig>().withInitial(()->{
//                        return (FarmConfig)ctx.getBean("farmConfig");
//                    });
//                    
//                    System.out.println("[ Current Thread : "+ Thread.currentThread().getName() +" ||  Farm Name : " + randomConfig.get().getFarmName() + " || Farm Profit :  " + randomConfig.get().getDailyProfit() + " ]");
//                }
//            },new Integer(i).toString()).start();
//            
//            
//            new Thread(new Runnable() {
//                
//                @Override
//                public void run()
//                {
//                    ThreadLocal<FarmConfig> randomConfig = new ThreadLocal<FarmConfig>().withInitial(()->{
//                        return (FarmConfig)ctx.getBean("farmConfig");
//                    });
//                    
//                    System.out.println("[ Current Thread : "+ Thread.currentThread().getName() +" ||  Farm Name : " + randomConfig.get().getFarmName() + " || Farm Profit :  " + randomConfig.get().getDailyProfit() + " ]");
//                }
//            },new Integer(i).toString()).start();
//        }
    }
}
