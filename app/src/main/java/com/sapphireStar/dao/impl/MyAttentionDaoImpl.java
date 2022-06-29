package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.android_project.DataBase.MySqlHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyAttention;
import com.sapphireStar.entity.PlaneTicket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyAttentionDaoImpl extends MySqlHelper implements MyAttentionDao {
    private SQLiteDatabase db;
    //private ContentValues values;
    public MyAttentionDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }
    @Override
    public List<Object[]> getMyAttention(String phone) throws SQLException {
        List<Object[]> list =new ArrayList<Object[]>();
        Object[] objects = null;
        getDatabase();
        String sql = "select * from my_attention where phone = " + phone;
        MyAttention myAttention = null;
        preparedStatement = connection.prepareStatement(sql);
        cursor = preparedStatement.executeQuery(sql);
        if(!cursor.next()) {
            closeDatabase();
            return null;
        }
        FlightDao flightDao = new FlightDaoImpl(db);
        cursor.beforeFirst();
        while(cursor.next()){
            objects = new Object[2];
            myAttention = new MyAttention();
            myAttention.setPlane_ticket_number(cursor.getInt("plane_ticket_number"));
            myAttention.setPhone(cursor.getString("phone"));
            objects = flightDao.GetFlightsByPT(String.valueOf(myAttention.getPlane_ticket_number()));
            list.add(objects);
        }
        closeDatabase();
        return list;
    }

    @Override
    public void addMyAttention(int plane_ticket_number, String phone) throws SQLException {
        String sql = "insert into my_attention(plane_ticket_number,phone) values ("+ plane_ticket_number +","+phone+")";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
    }

    @Override
    public void removeMyAttention(int plane_ticket_number, String phone) throws SQLException {
        String sql = "delete from my_attention where phone = " +phone +" and plane_ticket_number = " + plane_ticket_number;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
    }
}
