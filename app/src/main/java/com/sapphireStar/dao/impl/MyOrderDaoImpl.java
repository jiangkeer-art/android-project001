package com.sapphireStar.dao.impl;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sapphireStar.util.MySqlHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.entity.MyOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyOrderDaoImpl  extends MySqlHelper implements MyOrderDao {
    @Override
    public List<Object[]> getMyOrderByPhone(String phone,String state) throws SQLException {
        Object[] objects = null;
        String test_state = "",planeTicket="";
        getDatabase();

        String sql = "select * from my_order where phone = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,phone);
        cursor = preparedStatement.executeQuery();


        List<MyOrder> orderList = new ArrayList<MyOrder>();
        List<Object[]> list = new ArrayList<Object[]>();
        FlightDao flightDao = new FlightDaoImpl();

        MyOrder myOrder = null;
        while(cursor.next()){
            objects = new Object[3];
            myOrder = new MyOrder();
            myOrder.setOrder_number(cursor.getInt("order_number"));
            myOrder.setPhone(cursor.getString("phone"));
            myOrder.setPlane_ticket_number(cursor.getInt("plane_ticket_number"));
            test_state = cursor.getString("state");
            if(test_state.equals(state)){
                myOrder.setState(cursor.getString("state"));
                planeTicket=String.valueOf(myOrder.getPlane_ticket_number());
                Object[] o = flightDao.GetFlightsByPT(planeTicket);
                objects[0] = o[0];
                objects[1] = o[1];
                objects[2] = myOrder;
                list.add(objects);
            }
        }
        closeDatabase();
        return list;
    }

    @Override
    public void addMyOrder(int plane_ticket_number, String phone, int order_number,int state) throws SQLException {
        getDatabase();
        String sql = "insert into my_order(order_number,phone,plane_ticket_number,state) values ('"+ order_number +"','"+ phone +"',"+plane_ticket_number+","+state+")";
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
