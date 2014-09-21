package com.socialcard.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Toast;
import cn.sharp.android.ncr.NameCardRec;
import cn.sharp.android.ncr.StaticRecFromCamera;
import com.nfc.Beam;
import com.socialcard.myapp.StaticValues;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Cai on 14-4-26.
 */
public class ButtonActivity extends Activity {
    private File tempFile;
    String url;
    private Button contect,qrcode,design,input,help,set,scan,importbt,bgbt;
    private Animation anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8;
    private Animation reverse1,reverse2,reverse3,reverse4,reverse5,reverse6,reverse7,reverse8;
    private Animation rotate_out;
    private boolean isClick=false;
    private RotateAnimation myAnimation_Rotate;
    private  String name,phone,email;
    public static final int PICK_CONTACT_RESULT = 120;
    private final static int SCANNIN_GREQUEST_CODE = 2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.activity_button);
        init();
    }
    public void init(){
        myAnimation_Rotate=new RotateAnimation(0.0f, +350.0f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        rotate_out= AnimationUtils.loadAnimation(this, R.anim.rotate_out);
        rotate_out.setFillAfter(true);
        anim1=AnimationUtils.loadAnimation(this, R.anim.anim1);
        anim1.setFillAfter(true);
        anim2=AnimationUtils.loadAnimation(this, R.anim.anim2);
        anim2.setFillAfter(true);
        anim3=AnimationUtils.loadAnimation(this, R.anim.anim3);
        anim3.setFillAfter(true);
        anim4=AnimationUtils.loadAnimation(this, R.anim.anim4);
        anim4.setFillAfter(true);
        anim5=AnimationUtils.loadAnimation(this, R.anim.anim5);
        anim5.setFillAfter(true);
        anim6=AnimationUtils.loadAnimation(this, R.anim.anim6);
        anim6.setFillAfter(true);
        anim7=AnimationUtils.loadAnimation(this, R.anim.anim7);
        anim7.setFillAfter(true);
        anim8=AnimationUtils.loadAnimation(this, R.anim.anim8);
        anim8.setFillAfter(true);
        reverse1=AnimationUtils.loadAnimation(this, R.anim.reverse1);
        reverse1.setFillAfter(true);
        reverse2=AnimationUtils.loadAnimation(this, R.anim.reverse2);
        reverse2.setFillAfter(true);
        reverse3=AnimationUtils.loadAnimation(this, R.anim.reverse3);
        reverse3.setFillAfter(true);
        reverse4=AnimationUtils.loadAnimation(this, R.anim.reverse4);
        reverse4.setFillAfter(true);
        reverse5=AnimationUtils.loadAnimation(this, R.anim.reverse5);
        reverse5.setFillAfter(true);
        reverse6=AnimationUtils.loadAnimation(this, R.anim.reverse6);
        reverse6.setFillAfter(true);
        reverse7=AnimationUtils.loadAnimation(this, R.anim.reverse7);
        reverse7.setFillAfter(true);
        reverse8=AnimationUtils.loadAnimation(this, R.anim.reverse8);
        reverse8.setFillAfter(true);
        contect=(Button) findViewById(R.id.contect);
        qrcode=(Button) findViewById(R.id.qrcode);
        design=(Button) findViewById(R.id.design);
        input=(Button) findViewById(R.id.input);
        help=(Button) findViewById(R.id.help);
        set=(Button) findViewById(R.id.set);
        scan=(Button) findViewById(R.id.scan);
        importbt=(Button) findViewById(R.id.importbt);
        bgbt=(Button) findViewById(R.id.bgbt);
        bgbt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(isClick==false){
                    isClick=true;
                    bgbt.startAnimation(myAnimation_Rotate);
                    myAnimation_Rotate.setDuration(500);
                    contect.startAnimation(anim2);
                    anim2.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            contect.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });
                    qrcode.startAnimation(anim4);
                    anim4.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            qrcode.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });

                    design.startAnimation(anim1);
                    anim1.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            design.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });
                    input.startAnimation(anim3);
                    anim3.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            input.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }
                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });
                    help.startAnimation(anim6);
                    anim6.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            help.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });
                    set.startAnimation(anim5);
                    anim5.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            set.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });
                    scan.startAnimation(anim8);
                    anim8.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            scan.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });
                    importbt.startAnimation(anim7);
                    anim7.setAnimationListener(new Animation.AnimationListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onAnimationStart(Animation arg0) {
                            // TODO Auto-generated method stub
                            importbt.setAlpha(1);
                        }
                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                        }
                    });

                }else{
                    isClick=false;
                    bgbt.startAnimation(rotate_out);
                    myAnimation_Rotate.setDuration(500);
                    contect.startAnimation(reverse3);
                    qrcode.startAnimation(reverse4);
                    design.startAnimation(reverse1);
                    input.startAnimation(reverse2);
                    help.startAnimation(reverse6);
                    set.startAnimation(reverse5);
                    scan.startAnimation(reverse8);
                    importbt.startAnimation(reverse7);
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                            // TODO Auto-generated method stub
//                            Intent it=new Intent(ButtonActivity.this,PrimaryActivity.class);
//                            startActivity(it);
                            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);

                        }
                    }, 450);
                }
            }
        });
        design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Select();
            }
        });
        contect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contectIt = new Intent(ButtonActivity.this, Beam.class);
                startActivity(contectIt);
            }
        });

        input.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent it=new Intent(ButtonActivity.this,InputActivity.class);
                startActivity(it);
            }
        });
        importbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ButtonActivity.this,SortMainActivity.class);
                startActivity(intent);
          //      pickContact();
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // Intent scanit = new Intent(ButtonActivity.this, NameCardRec.class);
                Intent scanit = new Intent(ButtonActivity.this,StaticRecFromCamera.class);
                startActivity(scanit);
               // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              //  startActivityForResult(intent, 1);

            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setit=new Intent(ButtonActivity.this,SetActivity.class);
                startActivity(setit);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent helpit=new Intent(ButtonActivity.this,HelperActivity.class);
                startActivity(helpit);
            }
        });
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent scanit=new Intent(ButtonActivity.this, ScanActivity.class);
              //  startActivity(scanit);
                Intent intent = new Intent();
                intent.setClass(ButtonActivity.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);

            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();

                    Toast.makeText(getApplicationContext(), bundle.getString("result"),
                            Toast.LENGTH_SHORT).show();



                }
                break;

            case 3:
               // imageViewuser.setImageDrawable(Drawable.createFromPath(tempFile.getAbsolutePath()));
                // imageViewuser.setImageURI(Uri.fromFile(tempFile.getAbsoluteFile()));
                if (StaticValues.imagepath == null)
                    break;
                StaticValues.imagepath=tempFile.getAbsolutePath();
                PrimaryActivity a = new PrimaryActivity();
                a.ActivityA.recreate();
                break;
    }
    }
    //保存图片到本地
    private void addImageGallery(String file) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, new File(file).getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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

        Intent wrapperIntent = Intent.createChooser(innerIntent, "选择背景图片"); //��ʼ �����ñ���
        startActivityForResult(wrapperIntent, 3); // �践�� ��Ϊ 1  onActivityResult �е� requestCode ��Ӧ
    }



    //导入联系人
   /* private void pickContact(){

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);			// ָ��Intent
        super.startActivityForResult(intent, PICK_CONTACT_RESULT );// ����Intent
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_CONTACT_RESULT:									// ���շ��ص����
                Uri ret = data.getData();									// �������Uri
                String phoneSelection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                        + "=?"; 										// ���ò�ѯ����
                String[] phoneSelectionArgs = { String.valueOf(ContentUris
                        .parseId(ret)) }; 									// ��ѯ����
                Cursor c = super.managedQuery(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        phoneSelection, phoneSelectionArgs, null);			// ��ѯȫ���ֻ����
                StringBuffer namebuf = new StringBuffer() ;					// ���ڽ���ȫ������

                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) { 	// ѭ��ȡ���
                    namebuf.append(c.getString(c.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                    // ȡ������
                }
                StringBuffer phonebuf = new StringBuffer();					// ���ڽ���ȫ���绰
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) { 	// ѭ��ȡ���
                    phonebuf.append(c.getString(c.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    // ȡ���绰����
                }



                name=namebuf.toString();
                phone=phonebuf.toString();
                ContactsService dbHelper=new ContactsService(ButtonActivity.this,"contacts.db",1);
                SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("tel",phone);

                sqliteDatabase.insert("contacts", null, values);
                Toast.makeText(getApplicationContext(), "导入成功",
                        Toast.LENGTH_SHORT).show();
        }
    }*/

}