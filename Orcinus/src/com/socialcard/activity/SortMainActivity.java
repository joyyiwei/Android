package com.socialcard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.socialcard.myapp.StaticValues;
import com.socialcard.service.ContactsService;
import com.socialcard.sort.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortMainActivity extends Activity {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    private String curString;
    private ImageButton bt_back,bt_add;
    private final CharSequence[] items = { "查看", "编辑", "删除" };
    private  String name,phone;
    public static final int PICK_CONTACT_RESULT = 120;
    /**
     * ����ת����ƴ������
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    private String string[];
    /**
     * ���ƴ��������ListView����������
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_main);
        initViews();
    }

    private void initViews() {
        //ʵ����תƴ����
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        //�����Ҳഥ������
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);


       /* sortListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               TextView content = (TextView) view.findViewById(R.id.title);
                curString = content.getText().toString();
                AlertDialog myDialog = getAlertDialog();
                myDialog.show();
                return true;
            }
        });*/
        sortListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                /*Intent intent = new Intent();
                TextView content = (TextView) view.findViewById(R.id.title);
                intent.putExtra("name", content.getText().toString());
                // intent.setClass(SortMainActivity.this, MySocialCardActivity.class);
                intent.setClass(SortMainActivity.this, CondetailActivity.class);
                startActivity(intent);*/
                TextView content = (TextView) view.findViewById(R.id.title);
                curString = content.getText().toString();
                AlertDialog myDialog = getAlertDialog();
                myDialog.show();


            }
        });

        // 接口在这,数据源
        //	SourceDateList = filledData(getResources().getStringArray(R.array.date));
        int n = getString();
        String arr[] = new String[n];
        for (int i = 0; i < n; i++)
            arr[i] = string[i];
        SourceDateList = filledData(arr);

        // ���a-z��������Դ���
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);


        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
        //������������ֵ�ĸı�����������
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        bt_back=(ImageButton)findViewById(R.id.sort_imageBtn_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent it =new Intent(SortMainActivity.this,PrimaryActivity.class);
                startActivity(it);
            }
        });
        bt_add=(ImageButton)findViewById(R.id.sort_add);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SortMainActivity.this);

                builder.setItems(getResources().getStringArray(R.array.Array), new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        // TODO 自动生成的方法存根
                        System.out.println(arg1);
                        if (arg1 == 0)
                        {
                        Intent it=new Intent(SortMainActivity.this,InputActivity.class);
                            startActivity(it);

                        }else if(arg1 == 1){

                            pickContact();
                        }

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onResume() {
        // 更新listview
        super.onResume();

        int n = getString();
        String arr[] = new String[n];
        for (int i = 0; i < n; i++)
            arr[i] = string[i];
        SourceDateList = filledData(arr);
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);
    }


    public AlertDialog getAlertDialog() {
        // 创建builder
        AlertDialog.Builder builder = new AlertDialog.Builder(
                SortMainActivity.this);
        builder.setTitle("列表对话框") // 标题
                .setIcon(android.R.drawable.ic_dialog_info) // icon
                .setCancelable(true) // 不响应back按钮
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SortMainActivity.this,
                                "选择了" + items[which], Toast.LENGTH_SHORT)
                                .show();
                        Intent it = new Intent();
                        if (which == 0) {
                            // 查看
                            TextView content = (TextView) findViewById(R.id.title);
                            it.putExtra("name", content.getText().toString());
                            it.setClass(SortMainActivity.this, CondetailActivity.class);
                            startActivity(it);
                        }
                        else if (which == 1) {
                            // 编辑
                            TextView content = (TextView) findViewById(R.id.title);
                            it.putExtra("name", content.getText().toString());
                            it.setClass(SortMainActivity.this, SortMainEditActivity.class);
                            startActivity(it);
                            adapter.notifyDataSetChanged();
                        }
                        else {
                           // 删除
                            ContactsService dbHelper = new ContactsService(SortMainActivity.this, "contacts.db", 1);
                            //只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
                            final SQLiteDatabase mDb = dbHelper.getReadableDatabase();
                            String sql = "select * from contacts where name=?";
                            Cursor cs = mDb.rawQuery(sql, new String[]{curString});
                            if (cs.moveToNext()) {
                                mDb.delete("contacts", "name=?", new String[]{curString});
                                adapter.notifyDataSetChanged();
                                onResume();
                            }
                        }
                    }
                });
        // 创建Dialog对象
        AlertDialog dlg = builder.create();
        return dlg;
    }

    /**
     * ΪListView������
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //����ת����ƴ��
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * ���������е�ֵ��������ݲ�����ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // ���a-z��������
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    public int getString() {
        ContactsService dbHelper = new ContactsService(SortMainActivity.this, "contacts.db", 1);
        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
        String sql = "select * from contacts where user_id=?";
        SharedPreferences sharedPreferences = getSharedPreferences(StaticValues.username, Context.MODE_PRIVATE);
//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
        String name = sharedPreferences.getString("username", "").trim();
        // 注意这里的username以后要设成常量
        Cursor cursor = sqliteDatabase.rawQuery(sql, new String[]{name});
        if (cursor.moveToFirst()) {
            int n = cursor.getCount();
            string = new String[n * 2];
            for (int i = 0; i < n; i++) {
                cursor.moveToPosition(i);
                String s = cursor.getString(cursor.getColumnIndex("name")).trim();
                string[i] = s;
            }
            return n;
            //    Toast.makeText(ExportActivity.this, s, Toast.LENGTH_LONG).show();
        }
        return 0;
    }
    private void pickContact(){

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
                ContactsService dbHelper=new ContactsService(SortMainActivity.this,"contacts.db",1);
                SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                SharedPreferences sharedPreferences = getSharedPreferences(StaticValues.username, Context.MODE_PRIVATE);
//getString()第二个参数为缺省值，如果preference中不存在该key，将返回缺省值
                String Name = sharedPreferences.getString("username", "").trim();
                // 注意这里的username以后要设成常量
                int intimacy = 0;

                values.put("Intimacy",intimacy);
                values.put("user_id",Name);
                values.put("name",name);
                values.put("tel",phone);

                sqliteDatabase.insert("contacts", null, values);
                Toast.makeText(getApplicationContext(), name+phone+"导入成功",
                        Toast.LENGTH_SHORT).show();
        }
    }

}
