package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.dao.AdministratorDao;
import com.sapphireStar.entity.Administrator;

public class AdministratorDaoImpl implements AdministratorDao {
    private SQLiteDatabase db;
    public AdministratorDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }
    @Override
    public Administrator getAdministratorByPhone(String phone) {
        Administrator administrator = null;
        Cursor cursor = db.query("Administrators_User",new String[]{"*"},"phone = "+ phone,null,null,null,null );
        administrator = new Administrator();
        cursor.moveToFirst();
        administrator.setPhone(cursor.getString(0));
        administrator.setId(cursor.getString(1));
        cursor.close();
        return administrator;
    }
}
