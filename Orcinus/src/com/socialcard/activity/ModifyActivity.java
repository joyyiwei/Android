package com.socialcard.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.socialcard.service.PassProservice;

/**
 * Created by Cai on 14-4-27.
 */
public class ModifyActivity extends Activity {

    ImageView back;
    Button bt_sure;
    EditText user;
    EditText problem;
    EditText pass;
    EditText newpass1,newpass2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        init();
    }
    public void init(){
        user = (EditText) findViewById(R.id.find_user);
        problem = (EditText) findViewById(R.id.find_problem);
        pass = (EditText) findViewById(R.id.find_pass);
        newpass1 = (EditText)findViewById(R.id.find_newpass);
        newpass2 = (EditText)findViewById(R.id.find_newpass1);
        back=(ImageView)findViewById(R.id.modify_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ModifyActivity.this,SetActivity.class);
                startActivity(it);
            }
        });
        bt_sure=(Button)findViewById(R.id.find_sure);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = user.getText().toString().trim();
                String pro = problem.getText().toString().trim();
                String pas = pass.getText().toString().trim();
                String newPass1 = newpass1.getText().toString().trim();
                String newPass2 = newpass2.getText().toString().trim();
                boolean flag = check(name,pro,pas);
                System.out.println(newPass1+" "+newPass2);
                if (!newPass1.equals(newPass2)){
                    Toast.makeText(ModifyActivity.this, "������ƥ�����", Toast.LENGTH_LONG).show();
                    return;
                }
                /*
                	public boolean login(String username,String password){
		                SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		                String sql="select * from user where username=? and password=?";
		                Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
                        //��ѯ�Ľ���ǵõ���һ����¼��ǰһ����������true�Ļ����ʹ���ҵ���
		                if(cursor.moveToFirst()==true){
			                cursor.close();
			            return true;
		                }
		                return false;
	}
                */
                if (flag){
                    // update
                    PassProservice dbHelper = new PassProservice(ModifyActivity.this,"user.db",1); //notic
                    SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    //           values.put("user",name);
                    //          values.put("problem",pro);
                    values.put("password",newPass1);

                    sqliteDatabase.update("user", values, "username=?", new String[] {name});
                    Toast.makeText(ModifyActivity.this,"update",Toast.LENGTH_LONG).show();
                    sqliteDatabase.close();
                }
                else Toast.makeText(ModifyActivity.this,"ƥ�����",Toast.LENGTH_LONG).show();
            }
        });

    }
    boolean check(String name, String pro, String pas){
        PassProservice dbHelper = new PassProservice(ModifyActivity.this,"pass.db",1); //notic
        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
        String sql="select * from passPro where username=? and problem=? and pass=?";
        Cursor cursor = sqliteDatabase.rawQuery(sql,new String[]{name,pro,pas});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
}