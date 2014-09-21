package com.socialcard.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.socialcard.dao.DatabaseHelper;
import com.socialcard.entity.User;

public class UserService {
	private DatabaseHelper dbHelper;
	public UserService(Context context){
		dbHelper=new DatabaseHelper(context);
	}
	
	//��¼��
	public boolean login(String username,String password){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="select * from user where username=? and password=?";
		Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        //查询的结果是得到第一条记录的前一条，如果可以true的话，就代表找到了
		if(cursor.moveToFirst()==true){
			cursor.close();
			return true;
		}
		return false;
	}
	//ע����
	public boolean register(User user){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="insert into user(username,password,sex) values(?,?,?)";
		Object obj[]={user.getUsername(),user.getPassword(),user.getSex()};
		sdb.execSQL(sql, obj);
        sdb.close();
		return true;
	}
}
