package com.sapphireStar.dao.impl;

import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.util.MySqlHelper;
import com.sapphireStar.dao.AdministratorDao;
import com.sapphireStar.entity.Administrator;

import java.sql.SQLException;

public class AdministratorDaoImpl extends MySqlHelper implements AdministratorDao {


    @Override
    public Administrator getAdministratorByPhone(String phone) throws SQLException {
        getDatabase();
        String sql = "select * from administrators_user where phone = " + phone;
        preparedStatement = connection.prepareStatement(sql);
        cursor = preparedStatement.executeQuery();
        Administrator administrator = null;
        administrator = new Administrator();
        cursor.beforeFirst();
        cursor.next();
        administrator.setPhone(cursor.getString("phone"));
        administrator.setId(cursor.getString("id"));
        closeDatabase();
        return administrator;
    }
}
