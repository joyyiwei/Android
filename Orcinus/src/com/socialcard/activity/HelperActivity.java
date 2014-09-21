package com.socialcard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.socialcard.myapp.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cai on 14-4-27.
 */
public class HelperActivity extends Activity {
ImageButton bt_help_back,bt_help_set;
    MyApplication loginstate;
    private  String[] names= new String []{"注册登录","版本","退出"};

    private int[] imageid=new int[]{R.drawable.icon_login,R.drawable.icon_help,R.drawable.icon_update};

        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

            List<Map<String,Object>> helplistItems =new ArrayList<Map<String, Object>>();

            for(int i=0;i<names.length;i++){

                Map<String,Object> listItem=new HashMap<String, Object>();

                listItem.put("Icon",imageid[i]);
                listItem.put("Itemname",names[i]);
                helplistItems.add(listItem);




            }
            SimpleAdapter simpleAdapter=new SimpleAdapter(this,helplistItems,R.layout.setlist_item,
                    new String[]{"Icon","Itemname"},
                    new int[]{R.id.setlistview_item_icon,R.id.setlistview_item_text});
            simpleAdapter.notifyDataSetChanged();

            ListView helplist=(ListView)findViewById(R.id.helplistView);

            helplist.setAdapter(simpleAdapter);
            helplist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:
                             Toast.makeText(HelperActivity.this,"注册登录",5).show();
                            Intent loginit=new Intent(HelperActivity.this,LoginActivity.class);
                            startActivity(loginit);
                            break;
                        case 1:
                            //ImageView img=new ImageView(HelperActivity.this);
                            //img.setImageResource(R.drawable.icon1);
                            new AlertDialog.Builder(HelperActivity.this)
                            .setTitle("版本信息")
                            .setIcon(android.R.drawable.ic_dialog_info)
                           	.setItems(new String[]{"名称：orcinus", "版本号：1.0", "完成日期:2014/6/17"}, null)
                           	.setNegativeButton("确定", null)
                           	.show();
                             Toast.makeText(HelperActivity.this,"帮助",5).show();

                            break;
                        case 2:
                            loginstate=((MyApplication)getApplication());
                            loginstate.setLogintag(false);
                            Intent out=new Intent(HelperActivity.this,PrimaryActivity.class);
                            startActivity(out);
                            Toast.makeText(HelperActivity.this,"退出",5).show();
                            break;


                    }
                }
            });
     bt_help_back=(ImageButton)findViewById(R.id.help_imageBtn_back);
            bt_help_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it=new Intent(HelperActivity.this,PrimaryActivity.class);
                    startActivity(it);
                }
            });
            bt_help_set=(ImageButton)findViewById(R.id.help_imageBtn_set);
            bt_help_set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it1=new Intent(HelperActivity.this,SetActivity.class);
                    startActivity(it1);
                }
            });
        }
}