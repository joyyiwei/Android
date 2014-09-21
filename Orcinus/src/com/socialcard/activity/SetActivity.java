package com.socialcard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cai on 14-4-27.
 */
public class SetActivity extends Activity {
    private  String[] names= new String []{"我的名片","找回密码","修改密码"};

    private int[] imageid=new int[]{R.drawable.about_icon,R.drawable.icon_get_pwd,R.drawable.icon_change_pwd};

  ImageButton bt_set_back;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        List<Map<String,Object>> listItems =new ArrayList<Map<String, Object>>();

        for(int i=0;i<names.length;i++){

            Map<String,Object> listItem=new HashMap<String, Object>();

            listItem.put("Icon",imageid[i]);
            listItem.put("Itemname",names[i]);
            listItems.add(listItem);

        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.setlist_item,
                new String[]{"Icon","Itemname"},
                new int[]{R.id.setlistview_item_icon,R.id.setlistview_item_text});
        simpleAdapter.notifyDataSetChanged();

        ListView list=(ListView)findViewById(R.id.setlistView);

        list.setAdapter(simpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        // Toast.makeText(MyActivity.this,"我的名片",5).show();
                        Intent it0=new Intent(SetActivity.this,MySocialCardActivity.class);
                        startActivity(it0);
                        break;
                    case 1:
                        // Toast.makeText(MyActivity.this,"找回密码",5).show();
                        Intent it1=new Intent(SetActivity.this,FindpsdActivity.class);
                        startActivity(it1);
                        break;
                    case 2:
                        // Toast.makeText(MyActivity.this,"修改密码",5).show();
                        Intent it2=new Intent(SetActivity.this,ModifyActivity.class);
                        startActivity(it2);
                        break;


                }
            }
        });

bt_set_back=(ImageButton)findViewById(R.id.set_imageBtn_back);
        bt_set_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent=new Intent(SetActivity.this,PrimaryActivity.class);
                startActivity(itent);
            }
        });
    }
}