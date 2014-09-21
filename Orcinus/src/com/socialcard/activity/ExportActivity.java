package com.socialcard.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.socialcard.service.ContactsService;
import com.socialcard.myapp.StaticValues;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by acer on 14-5-1.
 */
public class ExportActivity extends Activity {

    ListView list;
    LazyAdapter adapter;
    ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

   //     ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("name","豆豆");
        songsList.add(map1);

        getData();
    /*    if (!getData()){
            Toast.makeText(ExportActivity.this,"δ��¼", Toast.LENGTH_LONG).show();
            return;
        }*/

        list = (ListView) findViewById(R.id.list);
        adapter = new LazyAdapter(this, songsList);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
              //  Toast.makeText(ExportActivity.this,"hehe",1).show();
                Intent intent = new Intent();
                intent.setClass(ExportActivity.this,ContactsActivity.class);
                startActivity(intent);
                // ����������ɷ��ӣ����粥��һ�׸���ȵ�
            }
        });
    }

    public boolean getData(){
     //   PassProservice dbHelper = new PassProservice(ExportActivity.this,"user.db",1); //notic
//        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        ContactsService dbHelper=new ContactsService(ExportActivity.this,"contacts.db",1);
        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
        String sql="select * from contacts where user_id=?";
        SharedPreferences sharedPreferences = getSharedPreferences(StaticValues.username, Context.MODE_PRIVATE);
        //getString()�ڶ�������Ϊȱʡֵ�����preference�в����ڸ�key��������ȱʡֵ
        String name = sharedPreferences.getString("username", "").trim();
        // ע�������username�Ժ�Ҫ��ɳ���
        Cursor cursor = sqliteDatabase.rawQuery(sql,new String[]{name});
        if (cursor.moveToFirst()){
            int n = cursor.getCount();
            for (int i = 0; i < n; i++) {
                cursor.moveToPosition(i);
                String s = cursor.getString(cursor.getColumnIndex("name")).trim();
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name",s);
                songsList.add(map);
            }
        //    Toast.makeText(ExportActivity.this, s, Toast.LENGTH_LONG).show();
        }
        /*if(cursor.moveToFirst()){//�ж��α��Ƿ�Ϊ��
                for(int i=0;i<cursor.getCount();i++){
                    cursor.move(i);//�ƶ���ָ����¼
                    String Name = cursor.getString(cursor.getColumnIndex("name")).trim();
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("name",Name);
                    songsList.add(map);
                }
                return true;
        }*/
        return false;
    }
}
