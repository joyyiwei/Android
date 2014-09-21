package com.socialcard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.socialcard.entity.User;
import com.socialcard.service.UserService;
/**
 * Created by Cai on 14-4-26.
 */
public class RegistActivity extends Activity {
    EditText username;
    EditText password;
    EditText age;
    RadioGroup sex;
    Button register;
    Button setprotect;
    boolean sign = false; //是否注册的标记
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        findViews();

        setprotect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sign) {
                    Intent intent1 = new Intent();
                    intent1.setClass(RegistActivity.this, PassProActivity.class);
                    String user = username.getText().toString().trim();
                    intent1.putExtra("name",user);
                    startActivity(intent1);
                }
                else Toast.makeText(RegistActivity.this, "请先注册", 1).show();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sign = true;
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String sexstr=((RadioButton)RegistActivity.this.findViewById(sex.getCheckedRadioButtonId())).getText().toString();
                Log.i("TAG", name + "_" + pass + "_" + "_" + sexstr);
                UserService uService=new UserService(RegistActivity.this);
                User user=new User();
                user.setUsername(name);
                user.setPassword(pass);
                user.setSex(sexstr);
                uService.register(user);
                Toast.makeText(RegistActivity.this, "注册成功,请设置密保", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void findViews() {
        username=(EditText) findViewById(R.id.usernameRegister);
        password=(EditText) findViewById(R.id.passwordRegister);
        sex=(RadioGroup) findViewById(R.id.sexRegister);
        register=(Button) findViewById(R.id.Register);
        setprotect=(Button)findViewById(R.id.bt_setProtect);
    }

    public String getUser() {
        String name = username.getText().toString().trim();
        return name;
    }
}