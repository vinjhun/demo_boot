package com.example_2.customBean;

public class Girl
{
    private Boy boy;

    public void setBoy(Boy boy)
    {
        this.boy = boy;
    }

    public void kiss()
    {
        boy.start();
        System.out.println("Girl kiss");
    }

}
