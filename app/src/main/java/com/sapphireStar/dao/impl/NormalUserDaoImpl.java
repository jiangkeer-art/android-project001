package com.sapphireStar.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBaseHelper;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.entity.User;

public class NormalUserDaoImpl implements NormalUserDao {
    private SQLiteDatabase db;
    //private ContentValues values;
    public NormalUserDaoImpl(DataBaseHelper dbHelper){
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public NormalUser getUserByPhone(String phone) {
        NormalUser normalUser = null;
        Cursor cursor = db.query("Normal_User",new String[]{"*"},"phone = "+ phone,null,null,null,null );
        normalUser = new NormalUser();
        cursor.moveToFirst();
        normalUser.setPhone(cursor.getString(0));
        normalUser.setId(cursor.getString(1));
        normalUser.setName(cursor.getString(2));
        normalUser.setIdNumber(cursor.getString(3));
        return normalUser;
    }

    @Override
    public int addUser(User user) {
        ContentValues valueUser = new ContentValues();

        valueUser.put("phone",user.getPhone());
        valueUser.put("password",user.getPassword());
        valueUser.put("is_administrators","0"); //1为管理员，0为普通用户
        db.insert("User",null,valueUser);
        return 0;
    }
}
