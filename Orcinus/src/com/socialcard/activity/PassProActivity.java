package com.socialcard.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.socialcard.service.PassProservice;

/**
 * Created by Cai on 14-4-26.
 */
public class PassProActivity extends Activity {
    private Button button;
    private EditText editTextPro;
    private EditText editTextPass;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passpro);
        editTextPro = (EditText) findViewById(R.id.editTextPro);
        editTextPass = (EditText) findViewById(R.id.edittextpass);
        button = (Button) findViewById(R.id.buttonInsert);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassProservice dbHelper = new PassProservice(PassProActivity.this,"pass.db",1); //notic
                SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
                System.out.println("创建成功");
                ContentValues values = new ContentValues();
                String problem = editTextPro.getText().toString().trim();
                String pass = editTextPass.getText().toString().trim();

                Intent intent = getIntent();
                String name = intent.getStringExtra("name");

                values.put("username",name);
                values.put("problem",problem);
                values.put("pass",pass);

                sqliteDatabase.insert("passPro",null,values);
                Toast.makeText(PassProActivity.this,"设置成功",1).show();
                System.out.println("Insert success");
                sqliteDatabase.close();
                Intent it=new Intent(PassProActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
    }
}