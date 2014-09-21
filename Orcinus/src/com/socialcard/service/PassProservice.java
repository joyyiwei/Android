package com.socialcard.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by acer on 14-4-7.
 */
public class PassProservice extends SQLiteOpenHelper{
    static String name="pass.db";
    static int dbVersion=1;
    public PassProservice(Context context) {
        super(context, name, null, dbVersion);
    }

    private static final int VERSION = 1;

    /**
     * ��SQLiteOpenHelper�����൱�У������иù��캯��
     * @param context	�����Ķ���
     * @param name		��ݿ����
     * @param factory
     * @param version	��ǰ��ݿ�İ汾��ֵ�������������ǵ�����״̬
     */
    public PassProservice(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        //����ͨ��super���ø��൱�еĹ��캯��
        super(context, name, factory, version);
    }

    public PassProservice(Context context, String name, int version){
        this(context,name,null,version);
    }

    public PassProservice(Context context, String name){
        this(context,name,VERSION);
    }

    @Override 
    public void onCreate(SQLiteDatabase db) {
        String sql="create table passPro(username varchar(20),problem varchar(20),pass varchar(20))";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
   //     db.execSQL("drop table passPro");
   //     db.execSQL("create table passPro(username varchar(20),problem varchar(20),pass varchar(20))");
        System.out.println("Update");
    }
}
