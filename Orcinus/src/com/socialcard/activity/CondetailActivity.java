package com.socialcard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ProgramStore;
import android.view.View;
import android.widget.TextView;
import com.socialcard.myapp.StaticValues;
import com.socialcard.service.ContactsService;

/**
 * Created by Administrator on 14-5-14.
 */
public class CondetailActivity extends Activity {
     TextView name1,name2,tel,company,position,email;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condetail);
        init();
    }
    public  void init(){
        name1=(TextView)findViewById(R.id.detailname1);
        name2=(TextView)findViewById(R.id.detailname2);
        tel=(TextView)findViewById(R.id.detailtel);
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CondetailActivity.this);

                builder.setItems(getResources().getStringArray(R.array.ItemArray), new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        // TODO 自动生成的方法存根
                        System.out.println(arg1);
                        if (arg1 == 0)
                        {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.CALL");
                            String  telephone=tel.getText().toString().trim();
                            intent.setData(Uri.parse("tel:" + telephone));
                            startActivity(intent);
                        }else if(arg1 == 1){

                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            String  telephone=tel.getText().toString().trim();
                            intent.setData( Uri.parse("smsto:"+telephone));
                            // intent.setType("vnd.android-dir/mms-sms");
                            startActivity(intent);
                        }

                    }
                   });
                builder.show();
               }
        });
        position=(TextView)findViewById(R.id.detailposition);
        company=(TextView)findViewById(R.id.detailcompany);
        email=(TextView)findViewById(R.id.detailemail);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String  address=email.getText().toString().trim();
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{address});
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "Chooser"));
            }
        });
        String username = this.getIntent().getStringExtra("name");
        ContactsService dbHelper=new ContactsService(CondetailActivity.this,"contacts.db",1);
        //只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
        SQLiteDatabase mDb = dbHelper.getReadableDatabase();

        String sql="select * from contacts where name=?";
        Cursor cs = mDb.rawQuery(sql,new String[]{username});
       // String sql="select * from contacts ";
       // Cursor cs = mDb.rawQuery(sql , null);
       if (cs.moveToNext()){
            // int n = cs.getCount();
          // cs.moveToPosition(n-1);
            String namestr = cs.getString(cs.getColumnIndex("name")).trim();
            String phonestr = cs.getString(cs.getColumnIndex("tel")).trim();
            String companystr = cs.getString(cs.getColumnIndex("company")).trim();
            String emailstr = cs.getString(cs.getColumnIndex("email")).trim();
            String poststr = cs.getString(cs.getColumnIndex("position")).trim();
            name1.setText(namestr);
            name2.setText(namestr);
            tel.setText(phonestr);
            company.setText(companystr);
            email.setText(emailstr);
            position.setText(poststr);
        }
    }
   }