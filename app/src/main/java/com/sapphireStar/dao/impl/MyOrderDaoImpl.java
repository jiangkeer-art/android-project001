package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public List<Object[]> getMyOrderByPhone(String phone,String state) throws SQLException {
        Object[] objects = null;
        String test_state = "",planeTicket="";
        getDatabase();

        String sql = "select * from my_order where phone = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,phone);
        cursor = preparedStatement.executeQuery();

//        if(!cursor.next()) {
//            closeDatabase();
//            return null;
//        }

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
            test_state = cursor.getString("state");
            Log.d("sqlllll", myOrder.getPhone()+" "+myOrder.getPlane_ticket_number());
            if(test_state.equals(state)){
                myOrder.setState(cursor.getString("state"));
                Log.d("sqllll", myOrder.getPhone()+" "+myOrder.getPlane_ticket_number());
                planeTicket=String.valueOf(myOrder.getPlane_ticket_number());
                objects = flightDao.GetFlightsByPT(planeTicket);
                list.add(objects);
            }
        }
        Log.d("sqlll",String.valueOf(list.size()));
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
    public void removeMyOrder(int plane_ticket_number, String phone) throws SQLException {
        getDatabase();
        String sql = "delete from my_order where phone = " +phone +" and plane_ticket_number = " + plane_ticket_number;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        closeDatabase();
    }
    @Override
    public int modifyState(String order_number) throws SQLException {
        getDatabase();

        String sql = "update my_order set state = 2 where order_number = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, order_number);
        if (preparedStatement.executeUpdate() == 0) {
            closeDatabase();
            return 1;
        }

        closeDatabase();
        return 0;
    }
}
