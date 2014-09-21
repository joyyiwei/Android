package com.socialcard.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 14-5-2.
 */
public class Information extends Activity {

    EditText phone,email;
    Button bt_send,bt_dial,bt_email;
String telephone,address;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        init();
    }
    public  void init(){
        phone= (EditText) findViewById(R.id.phoneedit);
        email= (EditText) findViewById(R.id.emailedit);
        bt_dial= (Button) findViewById(R.id.btdial);
        bt_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                telephone=phone.getText().toString().trim();
                intent.setData(Uri.parse("tel:" + telephone));
                startActivity(intent);
            }
        });
        bt_email= (Button) findViewById(R.id.btemail);
        bt_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address=email.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "Chooser"));

            }
        });
        bt_send= (Button) findViewById(R.id.btsendmsg);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                telephone=phone.getText().toString().trim();
                intent.setData( Uri.parse("smsto:"+telephone));
               // intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
            }
        });


    }
}