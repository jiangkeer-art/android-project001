package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.android_project.DataBase.MySqlHelper;
import com.sapphireStar.dao.AdministratorDao;
import com.sapphireStar.entity.Administrator;

import java.sql.SQLException;

public class AdministratorDaoImpl extends MySqlHelper implements AdministratorDao {
    private SQLiteDatabase db;
    public AdministratorDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }


    @Override
    public Administrator getAdministratorByPhone(String phone) throws SQLException {
        getDatabase();
        String sql = "select * from administrators_user where phone = " + phone;
        preparedStatement = connection.prepareStatement(sql);
        cursor = preparedStatement.executeQuery();
        Administrator administrator = null;
        //Cursor cursor = db.query("Administrators_User",new String[]{"*"},"phone = "+ phone,null,null,null,null );
        administrator = new Administrator();
        cursor.beforeFirst();
        cursor.next();
        administrator.setPhone(cursor.getString("phone"));
        administrator.setId(cursor.getString("id"));
        closeDatabase();
        return administrator;
    }
}
