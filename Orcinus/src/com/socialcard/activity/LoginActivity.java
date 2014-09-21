package com.socialcard.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.socialcard.service.UserService;
import com.socialcard.myapp.MyApplication;
import com.socialcard.myapp.StaticValues;

/**
 * Created by Cai on 14-4-26.
 */
public class LoginActivity extends Activity {
    Button bt_login,bt_login_regist;
    EditText username;
    EditText password;
    MyApplication loginstate;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    public  void init(){

        username=(EditText) findViewById(R.id.useredit);
        password=(EditText) findViewById(R.id.passwdedit);
        bt_login=(Button)findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                Log.i("TAG", name + "_" + pass);
                UserService uService=new UserService(LoginActivity.this);
                boolean flag=uService.login(name, pass);
                if(flag){
                    Log.i("TAG", "登录成功");
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    loginstate=((MyApplication)getApplication());
                    loginstate.setLogintag(true);
                    Intent itt=new Intent(LoginActivity.this,PrimaryActivity.class);
                    startActivity(itt);
                    // 记录到储存类
                    SharedPreferences sharedPreferences = getSharedPreferences(StaticValues.username, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                    editor.putString("username", name);
                    editor.commit();//提交修改

                }else{
                    Log.i("TAG","登录失败");
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        bt_login_regist=(Button)findViewById(R.id.bt_login_regist);
        bt_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registit = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(registit);
            }
        });
    }
}