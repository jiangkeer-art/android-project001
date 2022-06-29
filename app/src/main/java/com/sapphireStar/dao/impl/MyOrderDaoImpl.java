package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.android_project.DataBase.MySqlHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyOrderDaoImpl  extends MySqlHelper implements MyOrderDao {
    private SQLiteDatabase db;
    public MyOrderDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }
    @Override
    public List<Object[]> getMyOrderByPhone(String phone) throws SQLException {
        Object[] objects = null;
        getDatabase();

        String sql = "select * from my_order where phone = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,phone);
        cursor = preparedStatement.executeQuery();


        List<MyOrder> orderList = new ArrayList<MyOrder>();
        List<Object[]> list = new ArrayList<Object[]>();
        FlightDao flightDao = new FlightDaoImpl(db);

        //Cursor cursor = db.query("My_Order",new String[]{"*"},"phone = " + phone,null,null,null,null);
        MyOrder myOrder = null;
        while(cursor.next()){
            objects = new Object[2];
            myOrder = new MyOrder();
            myOrder.setOrder_number(cursor.getInt("order_number"));
            myOrder.setPhone(cursor.getString("phone"));
            myOrder.setPlane_ticket_number(cursor.getInt("plane_ticket_number"));
            myOrder.setState(cursor.getString("state"));
            objects = flightDao.GetFlightsByPT(String.valueOf(myOrder.getPlane_ticket_number()));
            list.add(objects);
        }

        closeDatabase();
        return list;
    }

    @Override
    public void addMyOrder(int plane_ticket_number, String phone, int order_number,int state) throws SQLException {
        getDatabase();
        String sql = "insert into my_order(order_number,phone,plane_ticket_number) values ("+ order_number +","+ phone +","+plane_ticket_number+","+state+")";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        closeDatabase();
    }

    @Override
    public void removeMyOrder(int plane_ticket_number, String phone, int order_number) throws SQLException {
        getDatabase();
        String sql = "delete from my_order where phone = " +phone +" and plane_ticket_number = " + plane_ticket_number;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        closeDatabase();
    }


}
