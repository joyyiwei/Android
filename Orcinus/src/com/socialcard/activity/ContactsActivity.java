package com.socialcard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acer on 14-5-4.
 */
public class ContactsActivity extends Activity{

	 private  String[] names= new String []{"默认","同事","朋友"};
	 String[] str=new String[]{"查看分组成员","修改分组名","删除分组"};
	 int[] imageid=new int[]{R.drawable.icon_look_groups,R.drawable.icon_add_groups,R.drawable.icon_delete_groups};
    PopupWindow pop;
    ImageView addgroup,back;
    String  groupname;
    EditText groupedit;
    View contentView;
    GridView gridView;

    List<Map<String,Object>> grouplistItems;
    List<Map<String,Object>>  poplistItems;
    SimpleAdapter simpleAdapter,popadapter;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        init();

    }
    public  void init(){

        grouplistItems =new ArrayList<Map<String, Object>>();

        for(int i=0;i<names.length;i++){

            Map<String,Object> listItem=new HashMap<String, Object>();

            listItem.put("Itemname",names[i]);
            grouplistItems.add(listItem);

        }
        simpleAdapter=new SimpleAdapter(this,grouplistItems,R.layout.setlist_item,
                new String[]{"Itemname"},
                new int[]{R.id.setlistview_item_text});
        simpleAdapter.notifyDataSetChanged();

        ListView list=(ListView)findViewById(R.id.grouplistView);

        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                contentView = getLayoutInflater().inflate(R.layout.popwindow, null,true);
                gridView = (GridView) contentView.findViewById(R.id.gridView);
                gridView.setAdapter(getAdapter());
                gridView.setOnItemClickListener(new ItemClickListener());
                pop = new PopupWindow(contentView,
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
                pop.setBackgroundDrawable(new BitmapDrawable());
                pop.setOutsideTouchable(true);
                pop.setAnimationStyle(R.style.animation);
                pop.showAsDropDown(findViewById(R.id.grouplistView));
            }
        });

        groupedit=new EditText(ContactsActivity.this);
        addgroup=(ImageView)findViewById(R.id.addgroupicon);
        addgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ContactsActivity.this)
                .setTitle("请输入您要添加的分组名")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(groupedit)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {               @Override
                            public void onClick(DialogInterface dialog, int which) {
                                groupname = groupedit.getText().toString().trim();
                                Map<String, Object> listItem = new HashMap<String, Object>();
                                listItem.put("Itemname", groupname);
                                grouplistItems.add(listItem);
                                simpleAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
        back= (ImageView) findViewById(R.id.groupback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(ContactsActivity.this,Information.class);
                startActivity(it);
            }
        });
    }
    private ListAdapter getAdapter() {
        poplistItems =new ArrayList<Map<String, Object>>();
        for(int j=0;j<names.length;j++){
            Map<String,Object> listItem=new HashMap<String, Object>();
            listItem.put("ItemIcon",imageid[j]);
            listItem.put("Itemname",str[j]);
            poplistItems.add(listItem);
        }
        popadapter=new SimpleAdapter(ContactsActivity.this,poplistItems,R.layout.grid_item,
                new String[]{"ItemIcon","Itemname"},
                new int[]{R.id.griditemimage,R.id.griditemtext});
        gridView.setAdapter(popadapter);
        return  popadapter;
    }

    private final class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            switch(position){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }
}
