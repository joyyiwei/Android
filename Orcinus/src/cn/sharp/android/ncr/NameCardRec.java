package cn.sharp.android.ncr;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.socialcard.activity.R;

/**
 * ��������
 */


public class NameCardRec extends Activity implements OnClickListener {
	private final static String TAG = "NameCardRec";

	private final static int MENU_PREF_SETTINGS = 0;
	private ImageButton btnReadFromSdcard;
	private ImageButton btnRecFromCamera;
	private ImageButton btnSetting;
	private ImageButton btnContact;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		//û�б�����
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);

		//��ݺ�������ͬ״̬    ���ò�ͬ����ʾ����
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			Log.e(TAG, "landscape");
			//����
			setContentView(R.layout.main_l);
		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			Log.e(TAG, "portrait");
			//����
			setContentView(R.layout.main);
		}

		//��ʼ��
		btnReadFromSdcard = (ImageButton) findViewById(R.id.btn_read_from_sdcard);
		btnRecFromCamera = (ImageButton) findViewById(R.id.btn_rec_from_camera);
		btnSetting = (ImageButton) findViewById(R.id.btn_setting);
		btnContact = (ImageButton) findViewById(R.id.btn_contact);
		btnSetting.setOnClickListener(this);
		btnReadFromSdcard.setOnClickListener(this);
		btnRecFromCamera.setOnClickListener(this);
		btnContact.setOnClickListener(this);
		
		Log.e(TAG, "leaving onCreate, tid:" + Thread.currentThread().getId());
	}

	//�˵���
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_PREF_SETTINGS:
			Intent prefIntent = new Intent(this, PrefSettings.class);
			startActivity(prefIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
   //����
	public void onClick(View v) {
		Log.e("________________", v.getContext().toString());
		if (v == btnReadFromSdcard) {
			//��sdcardʶ��
			Intent intent = new Intent(this, NamecardImageGallery.class);
			startActivity(intent);
		} else if (v == btnRecFromCamera) {
			//����ʶ��
			Intent intent = new Intent(this, StaticRecFromCamera.class);
			Log.e(TAG, "before starting static rec activity");
			startActivity(intent);
			Log.e(TAG, "static rec activity started");
		} else if (v == btnSetting) {
			//����
			Intent prefIntent = new Intent(this, PrefSettings.class);
			startActivity(prefIntent);
		} else if (v == btnContact) {
			Intent intent = new Intent(Intent.ACTION_VIEW, People.CONTENT_URI);
			startActivity(intent);
		}

	}
}