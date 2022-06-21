package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBaseHelper;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.entity.MyOrder;

import java.util.ArrayList;
import java.util.List;

public class MyOrderDaoImpl implements MyOrderDao {
    private SQLiteDatabase db;
    public MyOrderDaoImpl(DataBaseHelper dataBaseHelper){
        db = dataBaseHelper.getWritableDatabase();
    }
    @Override
    public List<MyOrder> getMyOrderByPhone(String phone) {
        List<MyOrder> list = new ArrayList<MyOrder>();
        Cursor cursor = db.query("My_Order",new String[]{"*"},"phone = " + phone,null,null,null,null);
        MyOrder myOrder = null;
        while(cursor.moveToNext()){
            myOrder = new MyOrder();
            myOrder.setOrder_number(cursor.getInt(0));
            myOrder.setPhone(cursor.getString(1));
            myOrder.setPlane_ticket_number(cursor.getInt(2));
            myOrder.setState(cursor.getString(3));
            list.add(myOrder);
        }
        return list;
    }
}
