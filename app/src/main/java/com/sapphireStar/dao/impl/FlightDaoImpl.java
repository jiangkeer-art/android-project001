package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.PlaneTicket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightDaoImpl implements FlightDao {
    private SQLiteDatabase db;
    public FlightDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }

    @Override
    public List<Object[]> GetFlights(String dateFind, String takeoff_city, String landing_city,int is_domestic,int is_direct_flight,int is_eco,int is_business,int is_share) {
        Object[] objects;
        String shipping_space;
        if(is_eco == 1 && is_business==1){
            shipping_space = "*";
        }
        else if(is_eco == 1 && is_business==0){
            shipping_space = "经济舱";
        }
        else if(is_eco == 0 && is_business==1){
            shipping_space = "商务舱";
        }
        else{
            shipping_space = "*";
        }
        String IDF;
        if(is_direct_flight == 0){
            IDF = "*";
        }
        else{
            IDF = "0";
        }
        String ISH;
        if(is_share == 0){
            ISH = "*";
        }
        else{
            ISH = "0";
        }

        Cursor cursor = db.query("Flight f, Plane_Ticket p",new String[]{"f.flight_number"
        ,"f.is_domestic"
        ,"f.takeoff_city"
        ,"f.landing_city"
        ,"f.transit_city"
        ,"f.takeoff_time"
        ,"f.punctuality_rate"
        ,"f.is_direct_flight"
        ,"f.is_share"
        ,"f.time_period"
        ,"f.airline_company"
        ,"f.food"
        ,"f.departure_terminal"
        ,"f.landing_terminal"
        ,"p.plane_ticket_number"
        ,"p.price"
        ,"p.shipping_space"
        ,"p.state"}
                ,"takeoff_time = " + dateFind +
                " and landing_city = " + landing_city +
                " and takeoff_city = "+ takeoff_city +
                " and is_domestic = " + is_domestic +
                " and is_share = " + ISH +
                " and is_direct_flight" + IDF +
                " and f.flight_number = p.flight_number" +
                " and f.takeoff_time = p.takeoff_time",null,null,null,null);
        if(cursor.getCount()<=0){
            return null;
        }
        Flight flight = null;
        PlaneTicket planeTicket = null;
        List<Object[]> list= new ArrayList<Object[]>();
        while(cursor.moveToNext()){
            objects = new Object[2];
            flight = new Flight();
            planeTicket = new PlaneTicket();
            flight.setFlight_number(cursor.getString(0));
            flight.setIs_domestic(cursor.getInt(1));
            flight.setTakeoff_city(cursor.getString(2));
            flight.setLanding_city(cursor.getString(3));
            flight.setTransit_city(cursor.getString(4));
            Date date = null;
            String str = cursor.getString(5);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = (Date)format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            flight.setTakeoff_time(date);
            flight.setPunctuality_rate(cursor.getDouble(6));
            flight.setIs_direct_flight(cursor.getInt(7));
            flight.setIs_share(cursor.getInt(8));
            flight.setTime_period(cursor.getString(9));
            flight.setAirline_company(cursor.getString(10));
            flight.setFood(cursor.getInt(11));
            flight.setDeparture_terminal(cursor.getString(12));
            flight.setLanding_terminal(cursor.getString(13));

            planeTicket.setPlane_ticket_number(cursor.getInt(14));
            planeTicket.setFlight_number(cursor.getString(0));
            planeTicket.setPrice(cursor.getInt(15));
            planeTicket.setShipping_space(cursor.getString(16));
            planeTicket.setState(cursor.getString(17));
            planeTicket.setTakeoff_time(date);
            objects[0] = flight;
            objects[1] = planeTicket;
            list.add(objects);
        }
        cursor.close();
        return list;
    }
}
