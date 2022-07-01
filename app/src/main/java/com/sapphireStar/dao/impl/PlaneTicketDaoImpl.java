package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sapphireStar.util.MySqlHelper;
import com.sapphireStar.dao.PlaneTicketDao;
import com.sapphireStar.entity.PlaneTicket;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaneTicketDaoImpl extends MySqlHelper implements PlaneTicketDao {
    @Override
    public List<PlaneTicket> getPlaneTicketByFlight(String flight_number) throws SQLException {
        getDatabase();
        List<PlaneTicket> list = new ArrayList<PlaneTicket>();
        String sql = "select * from Plane_Ticket where flight_number = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,flight_number);
        cursor = preparedStatement.executeQuery();
        PlaneTicket planeTicket = null;
        if(!cursor.next()){
            return null;
        }
        cursor.beforeFirst();
        while(cursor.next()){
            planeTicket = new PlaneTicket();
            planeTicket.setPlane_ticket_number(cursor.getInt("plane_ticket_number"));
            planeTicket.setFlight_number(cursor.getString("flight_number"));
            Date date = null;
            String str = cursor.getString("takeoff_time");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = (Date)format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            planeTicket.setTakeoff_time(date);
            planeTicket.setPrice(cursor.getInt("price"));
            planeTicket.setShipping_space(cursor.getString("shipping_space"));
            planeTicket.setState(cursor.getString("state"));
            list.add(planeTicket);
        }
        closeDatabase();
        return list;
    }
}
