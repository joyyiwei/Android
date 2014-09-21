package com.nfc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.socialcard.activity.R;

/**
 * Created by acer on 14-6-4.
 */
public class NfcSetActivity extends Activity{

    TextView name,phone,company,profession,email;
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam_set);
        button = (Button) findViewById(R.id.beamSet_button);
        name = (TextView) findViewById(R.id.beamSet_userName);
        phone = (TextView) findViewById(R.id.beamSet_userPhone);
        company = (TextView) findViewById(R.id.beamSet_company);
        profession = (TextView) findViewById(R.id.beamSet_userProfession);
        email = (TextView) findViewById(R.id.beamSet_userEmail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置常量为SetInfo
                SetInfo.flag = true;
                SetInfo.name = name.getText().toString().trim();
                SetInfo.company = company.getText().toString().trim();
                SetInfo.phone = phone.getText().toString().trim();
                SetInfo.email = email.getText().toString().trim();
                SetInfo.profession = profession.getText().toString().trim();
                Toast.makeText(NfcSetActivity.this, "Set Success.", 1).show();
                finish();
            }
        });
    }
}
