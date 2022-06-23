package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.dao.PlaneTicketDao;
import com.sapphireStar.entity.PlaneTicket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaneTicketDaoImpl implements PlaneTicketDao {
    private SQLiteDatabase db;
    public PlaneTicketDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }
    @Override
    public List<PlaneTicket> getPlaneTicketByFlight(String flight) {
        List<PlaneTicket> list = new ArrayList<PlaneTicket>();
        Cursor cursor = db.query("Plane_Ticket",new String[]{"*"},"flight_number = "+ "'"+flight+"'",null,null,null,null);
        PlaneTicket planeTicket = null;
        while(cursor.moveToNext()){
            planeTicket = new PlaneTicket();
            planeTicket.setPlane_ticket_number(cursor.getInt(0));
            planeTicket.setFlight_number(cursor.getString(1));
            Date date = null;
            String str = cursor.getString(2);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = (Date)format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            planeTicket.setTakeoff_time(date);
            planeTicket.setPrice(cursor.getInt(3));
            planeTicket.setShipping_space(cursor.getString(4));
            planeTicket.setState(cursor.getString(5));
            list.add(planeTicket);
        }
        cursor.close();
        return list;
    }
}
