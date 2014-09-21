package com.socialcard.myapp;

/**
 * Created by Administrator on 14-5-7.
 */
import android.app.Application;

/**
 * Created by Administrator on 14-5-6.
 */
public class MyApplication extends Application{
    private  boolean logintag=false;

    public  boolean getLogintag(){

        return logintag;
    }
    public  void setLogintag(boolean logintag){

        this.logintag=logintag;

    }
}