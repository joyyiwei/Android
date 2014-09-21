package com.socialcard.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.socialcard.service.ContactsService;

/**
 * Created by Administrator on 14-5-14.
 */
public class SortMainEditActivity extends Activity {
    private EditText name, tel, company, position, email;
    private Button button;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sortmainedit);
        init();

    }

    public void init() {
        name = (EditText) findViewById(R.id.sortMainName);
        tel = (EditText) findViewById(R.id.sortMainTell);
        company = (EditText) findViewById(R.id.sortMainCom);
        position = (EditText) findViewById(R.id.sortMainPos);
        email = (EditText) findViewById(R.id.sortMainEmail);
        button = (Button) findViewById(R.id.sortMainSure);

        final String username = this.getIntent().getStringExtra("name");
        ContactsService dbHelper = new ContactsService(SortMainEditActivity.this, "contacts.db", 1);
        //只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
        final SQLiteDatabase mDb = dbHelper.getReadableDatabase();

        String sql = "select * from contacts where name=?";
        Cursor cs = mDb.rawQuery(sql, new String[]{username});

        if (cs.moveToNext()) {
            // int n = cs.getCount();
            // cs.moveToPosition(n-1);
            String namestr = cs.getString(cs.getColumnIndex("name")).trim();
            // String phonestr = cs.getString(cs.getColumnIndex("tel")).trim();
            String companystr = cs.getString(cs.getColumnIndex("company")).trim();
            String emailstr = cs.getString(cs.getColumnIndex("email")).trim();
            String poststr = cs.getString(cs.getColumnIndex("position")).trim();
            String telstr = cs.getString(cs.getColumnIndex("tel")).trim();

            name.setText(namestr);
            tel.setText(telstr);
            company.setText(companystr);
            email.setText(emailstr);
            position.setText(poststr);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editName,editTel,editCompany,editPosition,editEmail;
                editName = name.getText().toString().trim();
                editCompany = company.getText().toString().trim();
                editEmail = email.getText().toString().trim();
                editPosition = position.getText().toString().trim();
                editTel = tel.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put("name", editName);
                values.put("tel", editTel);
                values.put("company", editCompany);
                values.put("email", editEmail);
                values.put("position", editPosition);
              //  sqliteDatabase.update("user", values, "username=?", new String[]{name});
                mDb.update("contacts", values, "name=?", new String[]{username});
                mDb.close();
                finish();
            }
        });
    }
}
