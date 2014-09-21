/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nfc;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.socialcard.activity.R;
import com.socialcard.myapp.StaticValues;
import com.socialcard.service.ContactsService;


public class Beam extends Activity implements CreateNdefMessageCallback,
        OnNdefPushCompleteCallback {
    NfcAdapter mNfcAdapter;
//    TextView mInfoText;
//    EditText emample1;
    Button button;
//    EditText emample2;
    private static final int MESSAGE_SENT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam_main);

        // 这里还少一个判断是否已经设置过了

        button = (Button) findViewById(R.id.beam_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beam.this, NfcSetActivity.class);
                startActivity(intent);
            }
        });
    //    mInfoText = (TextView) findViewById(R.id.beam_textView);
    //    emample1 = (EditText)findViewById(R.id.example_1);
    //    emample2 = (EditText) findViewById(R.id.example_2);
        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
     //       mInfoText = (TextView) findViewById(R.id.beam_textView);
     //       mInfoText.setText("NFC is not available on this device.");
              Toast.makeText(Beam.this, "NFC is not available on this device.", 1).show();
        } else {
            // Register callback to set NDEF message
            mNfcAdapter.setNdefPushMessageCallback(this, this);
            // Register callback to listen for message-sent success
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
    }


    /**
     * Implementation for the CreateNdefMessageCallback interface
     */
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
    //    String strEdit1 = emample1.getText().toString().trim();
    //    String strEdit2 = emample2.getText().toString().trim();
        // 好了可以取数据了
        String name = SetInfo.name;
        String phone = SetInfo.phone;
        String profession = SetInfo.profession;
        String company = SetInfo.company;
        String email = SetInfo.email;

        Time time = new Time();
        time.setToNow();

        String text = ("name:"+name+";phone:"+phone+";profession:"+profession+";company:"+company+";email:"+email);
        NdefMessage msg = new NdefMessage(NdefRecord.createMime(
                "application/com.example.android.beam", text.getBytes()));
        return msg;
    }

    /**
     * Implementation for the OnNdefPushCompleteCallback interface
     */
    @Override
    public void onNdefPushComplete(NfcEvent arg0) {
        // A handler is needed to send messages to the activity when this
        // callback occurs, because it happens from a binder thread
        mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();
    }

    /** This handler receives a message from onNdefPushComplete */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_SENT:
                Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_LONG).show();
                break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
  //      mInfoText.setText(new String(msg.getRecords()[0].getPayload()));
        String str = new String(msg.getRecords()[0].getPayload());
        NfcLunce.lunceSet(str);

        // 默认url为空
        ContactsService dbHelper=new ContactsService(Beam.this,"contacts.db",1);
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        SharedPreferences sharedPreferences = getSharedPreferences(StaticValues.username, Context.MODE_PRIVATE);
        //getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
        String Name = sharedPreferences.getString("username", "").trim();
        // 注意这里的username以后要设成常量
        int intimacy = 0;
        String url = "";
        values.put("Intimacy", intimacy);
        values.put("user_id", Name);
        values.put("name", NfcLunce.name);
        values.put("tel", NfcLunce.phone);
        values.put("company", NfcLunce.company);
        values.put("email", NfcLunce.email);
        values.put("position", NfcLunce.profession);
        values.put("URL", url);
        //  sqliteDatabase.insert("user_information", null, values);
        sqliteDatabase.insert("contacts", null, values);

        String text = (NfcLunce.name+" "+NfcLunce.company+" "+NfcLunce.email
                       +" "+NfcLunce.phone+" "+NfcLunce.profession);
        Toast.makeText(Beam.this, text, 1).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // If NFC is not available, we won't be needing this menu
        if (mNfcAdapter == null) {
            return super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
