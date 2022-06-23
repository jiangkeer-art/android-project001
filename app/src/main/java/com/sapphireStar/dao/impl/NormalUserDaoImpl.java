package com.sapphireStar.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.dao.AdministratorDao;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.entity.User;

public class NormalUserDaoImpl implements NormalUserDao {
    private SQLiteDatabase db;
    //private ContentValues values;
    public NormalUserDaoImpl(SQLiteDatabase sdb){
        db = sdb;
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
        cursor.close();
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

    @Override
    public int addNormalUSer(NormalUser normalUser) {
        ContentValues valueNormal_User=new ContentValues();
        valueNormal_User.put("phone",normalUser.getPhone());
        valueNormal_User.put("id",normalUser.getId());
        valueNormal_User.put("name",normalUser.getName());
        valueNormal_User.put("id_number",normalUser.getIdNumber());
        db.insert("Normal_User",null,valueNormal_User);
        return 0;
    }

    @Override
    public int modifyPassword(String phone, String newPassword,String reNewPassword,String oldPassword) {
        Cursor cursor = db.query("User",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                if(phone.equals(cursor.getString(0))){
                    if(oldPassword.equals(cursor.getString(1))){
                        if(newPassword.equals(reNewPassword)){
                            ContentValues values = new ContentValues();
                            values.put("password",newPassword);
                            db.update("User",values,"phone="+phone,null);
                        }
                    }
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return 0;
    }

    @Override
    public Object Login(String phone, String password) {
        Cursor cursor = db.query("User",new String[]{"*"},"phone = " + "'" + phone + "'" + " and password = " + "'" + password + "'",null,null,null,null);
        if(cursor.getCount() <= 0){
            return null;
        }
        cursor.moveToFirst();
        if(cursor.getInt(cursor.getColumnIndexOrThrow("is_administrators")) == 1){
            AdministratorDao administratorDao = new AdministratorDaoImpl(db);
            return administratorDao.getAdministratorByPhone(phone);
        }
        return getUserByPhone(phone);
    }
}
