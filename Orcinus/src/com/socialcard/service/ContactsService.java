package com.socialcard.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cai on 14-4-27.
 */
public class ContactsService  extends SQLiteOpenHelper {
    static String name="contacts.db";
    static int dbVersion=1;
    public ContactsService (Context context) {
        super(context, name, null, dbVersion);
    }

    private static final int VERSION = 1;

    /**
     * 在SQLiteOpenHelper的子类当中，必须有该构造函数
     * @param context	上下文对象
     * @param name		数据库名称
     * @param factory
     * @param version	当前数据库的版本，值必须是整数并且是递增的状态
     */
    public ContactsService (Context context, String name, SQLiteDatabase.CursorFactory factory,
                   int version) {
        //必须通过super调用父类当中的构造函数
        super(context, name, factory, version);
    }

    public ContactsService (Context context, String name, int version){
        this(context,name,null,version);
    }

    public ContactsService (Context context, String name){
        this(context,name,VERSION);
    }

    //ֻ�ڴ�����ʱ����һ��
    public void onCreate(SQLiteDatabase db) {
        String  sql = "create table contacts(name varchar(200) UNIQUE,user_id varchar(200),tel varchar(200),company varchar(200),email varchar(200),position varchar(200),URL varchar(200),Intimacy integer)";
        // db.execSQL("create table user(name varchar(20),company varchar(20),tel varchar(20),department varchar(20),position,varchar(20),email varchar(20))");
        db.execSQL(sql);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //     db.execSQL("drop table passPro");
        //     db.execSQL("create table passPro(username varchar(20),problem varchar(20),pass varchar(20))");
        System.out.println("Update");
    }
}
