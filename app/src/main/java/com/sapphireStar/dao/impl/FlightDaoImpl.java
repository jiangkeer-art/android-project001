package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.entity.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightDaoImpl implements FlightDao {
    private SQLiteDatabase db;
    public FlightDaoImpl(DataBaseHelper dataBaseHelper){
        db = dataBaseHelper.getWritableDatabase();
    }

    @Override
    public List<Flight> GetFlights(String dateFind, String takeoff_city, String landing_city) {
        Cursor cursor = db.query("Flight",new String[]{"*"},null,null,null,null,null);
        Flight flight = null;
        List<Flight> list = new ArrayList<Flight>();
        while(cursor.moveToNext()){
            flight = new Flight();
            flight.setFlight_number(cursor.getString(0));
            flight.setIs_domestic(cursor.getInt(1));
            flight.setTakeoff_city(cursor.getString(2));
            if(takeoff_city==null||takeoff_city.equals(flight.getTakeoff_city())){}else
                continue;
            flight.setLanding_city(cursor.getString(3));
            if(landing_city==null||landing_city.equals(flight.getLanding_city())){}else
                continue;
            flight.setTransit_city(cursor.getString(4));
            Date date = null;
            String str = cursor.getString(5);
            if(dateFind==null||str.equals(dateFind)){}else
                continue;
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
            list.add(flight);
        }
        return list;
    }
}
