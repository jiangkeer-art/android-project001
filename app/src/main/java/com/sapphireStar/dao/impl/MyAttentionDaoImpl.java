package com.sapphireStar.dao.impl;

import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.util.MySqlHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.entity.MyAttention;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyAttentionDaoImpl extends MySqlHelper implements MyAttentionDao {
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
        FlightDao flightDao = new FlightDaoImpl();
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
        getDatabase();
        String sql = "insert into my_attention(plane_ticket_number,phone) values ("+ plane_ticket_number +","+phone+")";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        closeDatabase();
    }

    @Override
    public void removeMyAttention(int plane_ticket_number, String phone) throws SQLException {
        getDatabase();
        String sql = "delete from my_attention where phone = " +phone +" and plane_ticket_number = " + plane_ticket_number;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        closeDatabase();
    }
}
