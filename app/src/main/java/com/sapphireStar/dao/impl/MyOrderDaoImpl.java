package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyOrder;

import java.util.ArrayList;
import java.util.List;

public class MyOrderDaoImpl implements MyOrderDao {
    private SQLiteDatabase db;
    public MyOrderDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }
    @Override
    public List<Object[]> getMyOrderByPhone(String phone) {
        Object[] objects = null;
        List<MyOrder> orderList = new ArrayList<MyOrder>();
        List<Object[]> list = new ArrayList<Object[]>();
        FlightDao flightDao = new FlightDaoImpl(db);
        Cursor cursor = db.query("My_Order",new String[]{"*"},"phone = " + phone,null,null,null,null);
        MyOrder myOrder = null;
        while(cursor.moveToNext()){
            objects = new Object[2];
            myOrder = new MyOrder();
            myOrder.setOrder_number(cursor.getInt(0));
            myOrder.setPhone(cursor.getString(1));
            myOrder.setPlane_ticket_number(cursor.getInt(2));
            myOrder.setState(cursor.getString(3));
            objects = flightDao.GetFlightsByPT(String.valueOf(myOrder.getPlane_ticket_number()));
            list.add(objects);
        }

        cursor.close();

        return list;
    }
}
