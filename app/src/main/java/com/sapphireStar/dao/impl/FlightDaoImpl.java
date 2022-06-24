package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
            shipping_space = "%";
        }
        else if(is_eco == 1 && is_business==0){
            shipping_space = "经济舱";
        }
        else if(is_eco == 0 && is_business==1){
            shipping_space = "头等舱";
        }
        else{
            shipping_space = "%";
        }
        String IDF;
        if(is_direct_flight == 0){
            IDF = "%";
        }
        else{
            IDF = "0";
        }
        String ISH;
        if(is_share == 0){
            ISH = "%";
        }
        else{
            ISH = "0";
        }
        if(dateFind.equals("")){
            dateFind = "%";
        }
//        Cursor cursor = db.query("Flight,Plane_Ticket",new String[]{"Flight.flight_number"
//        ,"Flight.is_domestic"
//        ,"Flight.takeoff_city"
//        ,"Flight.landing_city"
//        ,"Flight.transit_city"
//        ,"Flight.takeoff_time"
//        ,"Flight.punctuality_rate"
//        ,"Flight.is_direct_flight"
//        ,"Flight.is_share"
//        ,"Flight.time_period"
//        ,"Flight.airline_company"
//        ,"Flight.food"
//        ,"Flight.departure_terminal"
//        ,"Flight.landing_terminal"
//        ,"Plane_Ticket.plane_ticket_number"
//        ,"Plane_Ticket.price"
//        ,"Plane_Ticket.shipping_space"
//        ,"Plane_Ticket.state"}
//                ,"Flight.takeoff_time like " + "'"+ dateFind + "'"+
//                " and landing_city = " + "'" + landing_city + "'" +
//                " and takeoff_city = " + "'" + takeoff_city + "'" +
//                " and is_domestic = " + is_domestic +
//                " and is_share like " + "'" + ISH + "'" +
//                " and is_direct_flight like " + "'" + IDF + "'" +
//                " and Flight.flight_number = Plane_Ticket.flight_number" +
//                " and Flight.takeoff_time = Plane_Ticket.takeoff_time" +
//                " and Plane_Ticket.shipping_space like " + "'" + shipping_space + "'",null,null,null,null);
        Cursor cursor = db.rawQuery("SELECT Flight.flight_number, Flight.is_domestic, Flight.takeoff_city, Flight.landing_city, Flight.transit_city, Flight.takeoff_time, Flight.punctuality_rate, Flight.is_direct_flight, Flight.is_share, Flight.time_period, Flight.airline_company, Flight.food, Flight.departure_terminal, Flight.landing_terminal, Plane_Ticket.plane_ticket_number, Plane_Ticket.price, Plane_Ticket.shipping_space, Plane_Ticket.state " +
                        "FROM Flight,Plane_Ticket " +
                        "WHERE Flight.takeoff_time like ?"+
                        " and landing_city = ?" +
                        " and takeoff_city = ?" +
                        " and is_domestic = ?" +
                        " and is_share like ?" +
                        " and is_direct_flight like ?" +
                        " and Flight.flight_number = Plane_Ticket.flight_number" +
                        " and Flight.takeoff_time = Plane_Ticket.takeoff_time" +
                        " and Plane_Ticket.shipping_space like ?"
                ,new String[]{"%"+dateFind+"%",landing_city,takeoff_city,String.valueOf(is_domestic),ISH,IDF,shipping_space});
        Log.d("test",
                "\nWHERE Flight.takeoff_time like "+ "%"+dateFind+"%" +"\n"+
                " and landing_city = " + landing_city +"\n"+
                " and takeoff_city = " + takeoff_city +"\n"+
                " and is_domestic = " + String.valueOf(is_domestic) +"\n"+
                " and is_share like " + ISH +"\n"+
                " and is_direct_flight like " + IDF +"\n"+
                " and Flight.flight_number = Plane_Ticket.flight_number" +"\n"+
                " and Flight.takeoff_time = Plane_Ticket.takeoff_time" +"\n"+
                " and Plane_Ticket.shipping_space like "+ shipping_space );
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

            Log.d("testtest", flight.getFlight_number());
            list.add(objects);
        }
        cursor.close();
        return list;
    }

    @Override
    public Object[] GetFlightsByPT(String plane_ticket_number) {
        Object[] objects;
        Cursor cursor = db.rawQuery("SELECT Flight.flight_number, Flight.is_domestic, Flight.takeoff_city, Flight.landing_city, Flight.transit_city, Flight.takeoff_time, Flight.punctuality_rate, Flight.is_direct_flight, Flight.is_share, Flight.time_period, Flight.airline_company, Flight.food, Flight.departure_terminal, Flight.landing_terminal, Plane_Ticket.plane_ticket_number, Plane_Ticket.price, Plane_Ticket.shipping_space, Plane_Ticket.state " +
                        "FROM Flight,Plane_Ticket " +
                        "WHERE Plane_Ticket.plane_ticket_number = ?" +
                        " and Flight.flight_number = Plane_Ticket.flight_number" +
                        " and Flight.takeoff_time = Plane_Ticket.takeoff_time"
                ,new String[]{plane_ticket_number});
        Flight flight = null;
        PlaneTicket planeTicket = null;
        if(cursor.getCount()<=0){
            return null;
        }
        List<Object[]> list= new ArrayList<Object[]>();
        cursor.moveToFirst();
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
            date = (Date) format.parse(str);
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
        return objects;
    }
}
