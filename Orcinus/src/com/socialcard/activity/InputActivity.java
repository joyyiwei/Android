package com.socialcard.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.socialcard.service.ContactsService;
import com.socialcard.myapp.StaticValues;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Cai on 14-4-26.
 */
public class InputActivity extends Activity {

    private File tempFile;
    String url;
    ImageButton save,back;
    EditText editTextname;
    EditText editTexttel;
    EditText editTextcom;
    EditText editTextemail;
    EditText editTextpos;
    ImageView imageViewuser;
    String name,email,phone;
    String result;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        init();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavetoSql();
                Intent saveit=new Intent(InputActivity.this,PrimaryActivity.class);
                startActivity(saveit);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(InputActivity.this,PrimaryActivity.class);
                startActivity(it);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });

        imageViewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Select();
            }
        });
    }

    public void init() {
        imageViewuser = (ImageView)findViewById(R.id.imageView_user);
        back= (ImageButton) findViewById(R.id.imageBtn_back);
        save = (ImageButton) findViewById(R.id.imageBtn_save);
        editTextcom = (EditText) findViewById(R.id.edit_company);
        editTextemail = (EditText) findViewById(R.id.edit_userEmail);
        editTextname = (EditText) findViewById(R.id.edit_userName);
        editTextpos = (EditText) findViewById(R.id.edit_userProfessional);
        editTexttel= (EditText) findViewById(R.id.edit_userPhone);
       /* Bundle bundle = this.getIntent().getExtras();
        name=bundle.getString("name");
        name=bundle.getString("email");
        name=bundle.getString("phone");
        editTextname.setText(name);
        editTextemail.setText(email);
        editTexttel.setText(phone);*/
    }

    public void SavetoSql() {
        ContactsService dbHelper=new ContactsService(InputActivity.this,"contacts.db",1);
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        System.out.println("�����ɹ�");

        ContentValues values = new ContentValues();
        String name = editTextname.getText().toString().trim();
        String tel = editTexttel.getText().toString().trim();
        String company = editTextcom.getText().toString().trim();
        String postion = editTextpos.getText().toString().trim();
        String email = editTextcom.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences(StaticValues.username, Context.MODE_PRIVATE);
//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
        String Name = sharedPreferences.getString("username", "").trim();
        // 注意这里的username以后要设成常量
        int intimacy = 0;

        values.put("Intimacy",intimacy);
        values.put("user_id",Name);
        values.put("name",name);
        values.put("tel",tel);
        values.put("company",company);
        values.put("email",email);
        values.put("position",postion);
        values.put("URL",url);
      //  sqliteDatabase.insert("user_information", null, values);
        sqliteDatabase.insert("contacts", null, values);
        Toast.makeText(getApplicationContext(), "保存成功",
                Toast.LENGTH_SHORT).show();
        System.out.println("insert success");
    }

    @SuppressLint("SdCardPath")
    public void Select() {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        innerIntent.putExtra("crop", "true");// ���ܳ�������С���򣬲�Ȼû�м������ܣ�ֻ��ѡȡͼƬ
        innerIntent.putExtra("aspectX", 1); // ���ַŴ����С
        innerIntent.setType("image/*"); // �鿴���� ��ϸ�������� com.google.android.mms.ContentType

        tempFile =new File("/sdcard/ll1x/"+ Calendar.getInstance().getTimeInMillis()+".png"); // ��ʱ����Ϊ�ļ���
        url = tempFile.getAbsolutePath().toString();
        File temp = new File("/sdcard/UserImage/");//������Ŀ �ļ���
        if (!temp.exists()) {
            temp.mkdir();
        }
        innerIntent.putExtra("output", Uri.fromFile(tempFile));  // ר��Ŀ���ļ�
        innerIntent.putExtra("outputFormat", "JPEG"); //�����ļ���ʽ

        Intent wrapperIntent = Intent.createChooser(innerIntent, "选择图片"); //��ʼ �����ñ���
        startActivityForResult(wrapperIntent, 1); // �践�� ��Ϊ 1  onActivityResult �е� requestCode ��Ӧ
    }

    //���óɹ����ط���
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(tempFile.getAbsolutePath()!=null){
                imageViewuser.setImageDrawable(Drawable.createFromPath(tempFile.getAbsolutePath()));
                // imageViewuser.setImageURI(Uri.fromFile(tempFile.getAbsoluteFile()));

              }
            break;
        }
    }
}