package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.entity.MyAttention;

import java.util.ArrayList;
import java.util.List;

public class MyAttentionDaoImpl implements MyAttentionDao {
    private SQLiteDatabase db;
    //private ContentValues values;
    public MyAttentionDaoImpl(SQLiteDatabase sdb, DataBaseHelper dbHelper){
        db = sdb;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }
    @Override
    public List<MyAttention> getMyAttention(String phone) {
        List<MyAttention> list =new ArrayList<MyAttention>();
        MyAttention myAttention = null;
        Cursor cursor = db.query("My_Attention",new String[]{"*"},"phone = "+ phone,null,null,null,null );
        while(cursor.moveToNext()){
            myAttention = new MyAttention();
            myAttention.setPlane_ticket_number(cursor.getInt(0));
            myAttention.setPhone(cursor.getString(1));
            list.add(myAttention);
        }
        db.close();
        return list;
    }
}
