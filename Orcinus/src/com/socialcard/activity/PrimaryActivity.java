package com.socialcard.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.socialcard.myapp.StaticValues;

/**
 * Created by Cai on 14-4-26.
 */
public class PrimaryActivity extends Activity {
    ViewGroup vg;
    static Activity ActivityA;
    //MyApplication loginstate;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

       /* loginstate=((MyApplication)getApplication());
        boolean logintag1=loginstate.getLogintag();
        if(logintag1==true){
            init();
        }*/
        // 设置背景
      //Theme theme = Theme.getUniqueTheme();
       //theme.setUniqueTheme(vg);
        ActivityA = this;
        vg=(ViewGroup)findViewById(R.id.relativelayout);
        vg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent it=new Intent();
                it.setClass(PrimaryActivity.this,ButtonActivity.class);
                startActivityForResult(it, 0);


            }
        });
        if(StaticValues.imagepath!=null){
            vg.setBackground(Drawable.createFromPath(StaticValues.imagepath));

        }
    }

}