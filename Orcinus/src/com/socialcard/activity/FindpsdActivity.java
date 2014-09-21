package com.socialcard.activity;

import android.app.Activity;
import android.app.AlertDialog;
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
 * Created by Administrator on 14-6-17.
 */
public class FindpsdActivity extends Activity {
    ImageView back;
    Button bt_sure;
    EditText user;
    EditText problem;
    EditText pass;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpsd);
        init();
    }
    public void init(){
        user = (EditText) findViewById(R.id.fdpsd_user);
        problem = (EditText) findViewById(R.id.fdpsd_problem);
        pass = (EditText) findViewById(R.id.fdpsd_pass);
        back=(ImageView)findViewById(R.id.fdpsd_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindpsdActivity.this,SetActivity.class);
                startActivity(it);
            }
        });
        bt_sure=(Button)findViewById(R.id.fdpsd_sure);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = user.getText().toString().trim();
                String pro = problem.getText().toString().trim();
                String pas = pass.getText().toString().trim();
                boolean flag = check(name,pro,pas);
                if (flag){
                    // update
                    PassProservice dbHelper = new PassProservice(FindpsdActivity.this,"user.db",1); //notic
                    SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
                    String username=name;
                    String sql="select * from user where username=?  ";
                    Cursor cs = sqliteDatabase.rawQuery(sql ,new String[]{username});
                    if (cs.moveToNext()){
                        String password = cs.getString(cs.getColumnIndex("password")).trim();
                        new AlertDialog.Builder(FindpsdActivity.this)
                                .setTitle("找回密码")
                                .setIcon(android.R.drawable.ic_dialog_info)
                                .setItems(new String[]{"您的密码是：",password}, null)
                                .setNegativeButton("确定", null)
                                .show();
                    }

                    sqliteDatabase.close();
                }else{

                    new AlertDialog.Builder(FindpsdActivity.this)
                            .setTitle("系统消息")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setMessage("请输入正确的密保答案")
                            .setNegativeButton("确定", null)
                            .show();
                }
            }
        });

    }
    boolean check(String name, String pro, String pas){
        PassProservice dbHelper = new PassProservice(FindpsdActivity.this,"pass.db",1); //notic
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