package test.test;

public class App 
{
    public static void main( String[] args )
    {
    	long tenSecondsAfter = System.currentTimeMillis() + 10000;	//current time  + 10 second after
    	
    	long start = System.currentTimeMillis();
    	long lastFibo = 0;
    	long noOfFiboLoop = 0 ;
    	long f1 = 0;
    	long f2 = 1;
    	
    	while(System.currentTimeMillis() < tenSecondsAfter)
    	{
    		lastFibo = f1 + f2;
    		
    		f1 = f2;
    		f2 = lastFibo;
    		noOfFiboLoop++;
    	}
    	
    	long end = System.currentTimeMillis();
    	
    	System.out.println("The " + noOfFiboLoop  + " Fibonacci number is " + lastFibo);		
    	System.out.println("End at " + (end - start) + " milliseconds");
    }
    
    
}
