package com.sapphireStar.dao.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sapphireStar.android_project.DataBase.MySqlHelper;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.PlaneTicket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightDaoImpl extends MySqlHelper implements FlightDao {
    private SQLiteDatabase db;
    public FlightDaoImpl(SQLiteDatabase sdb){
        db = sdb;
    }

    @Override
    public List<Object[]> GetFlights(String dateFind, String takeoff_city, String landing_city,int is_domestic,int is_direct_flight,int is_eco,int is_business,int is_share) throws SQLException {
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
        getDatabase();
        String sql = "SELECT Flight.flight_number, Flight.is_domestic, Flight.takeoff_city, Flight.landing_city, Flight.transit_city, Flight.takeoff_time, Flight.punctuality_rate, Flight.is_direct_flight, Flight.is_share, Flight.time_period, Flight.airline_company, Flight.food, Flight.departure_terminal, Flight.landing_terminal, Plane_Ticket.plane_ticket_number, Plane_Ticket.price, Plane_Ticket.shipping_space, Plane_Ticket.state " +
                "FROM Flight,Plane_Ticket " +
                "WHERE Flight.takeoff_time like ?"+
                " and landing_city = ?" +
                " and takeoff_city = ?" +
                " and is_domestic = ?" +
                " and is_share like ?" +
                " and is_direct_flight like ?" +
                " and Flight.flight_number = Plane_Ticket.flight_number" +
                " and Flight.takeoff_time = Plane_Ticket.takeoff_time" +
                " and Plane_Ticket.shipping_space like ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,dateFind);
        preparedStatement.setString(2,landing_city);
        preparedStatement.setString(3,takeoff_city);
        preparedStatement.setString(4,String.valueOf(is_domestic));
        preparedStatement.setString(5,ISH);
        preparedStatement.setString(6,IDF);
        preparedStatement.setString(7,shipping_space);
        cursor = preparedStatement.executeQuery();
//        Cursor cursor = db.rawQuery("SELECT Flight.flight_number, Flight.is_domestic, Flight.takeoff_city, Flight.landing_city, Flight.transit_city, Flight.takeoff_time, Flight.punctuality_rate, Flight.is_direct_flight, Flight.is_share, Flight.time_period, Flight.airline_company, Flight.food, Flight.departure_terminal, Flight.landing_terminal, Plane_Ticket.plane_ticket_number, Plane_Ticket.price, Plane_Ticket.shipping_space, Plane_Ticket.state " +
//                        "FROM Flight,Plane_Ticket " +
//                        "WHERE Flight.takeoff_time like ?"+
//                        " and landing_city = ?" +
//                        " and takeoff_city = ?" +
//                        " and is_domestic = ?" +
//                        " and is_share like ?" +
//                        " and is_direct_flight like ?" +
//                        " and Flight.flight_number = Plane_Ticket.flight_number" +
//                        " and Flight.takeoff_time = Plane_Ticket.takeoff_time" +
//                        " and Plane_Ticket.shipping_space like ?"
//                ,new String[]{"%"+dateFind+"%",landing_city,takeoff_city,String.valueOf(is_domestic),ISH,IDF,shipping_space});
        if(!cursor.next()){
            return null;
        }
        cursor.beforeFirst();
        Flight flight = null;
        PlaneTicket planeTicket = null;
        List<Object[]> list= new ArrayList<Object[]>();
        while(cursor.next()){
            objects = new Object[2];
            flight = new Flight();
            planeTicket = new PlaneTicket();
            flight.setFlight_number(cursor.getString("Flight.flight_number"));
            flight.setIs_domestic(cursor.getInt("Flight.is_domestic"));
            flight.setTakeoff_city(cursor.getString("Flight.takeoff_city"));
            flight.setLanding_city(cursor.getString("Flight.landing_city"));
            flight.setTransit_city(cursor.getString("Flight.transit_city"));
            Date date = null;
            String str = cursor.getString("Flight.takeoff_time");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                date = (Date)format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            flight.setTakeoff_time(date);
            flight.setPunctuality_rate(cursor.getDouble("Flight.punctuality_rate"));
            flight.setIs_direct_flight(cursor.getInt("Flight.is_direct_flight"));
            flight.setIs_share(cursor.getInt("Flight.is_share"));
            flight.setTime_period(cursor.getString("Flight.time_period"));
            flight.setAirline_company(cursor.getString("Flight.airline_company"));
            flight.setFood(cursor.getInt("Flight.food"));
            flight.setDeparture_terminal(cursor.getString("Flight.departure_terminal"));
            flight.setLanding_terminal(cursor.getString("Flight.landing_terminal"));

            planeTicket.setPlane_ticket_number(cursor.getInt("Plane_Ticket.plane_ticket_number"));
            planeTicket.setFlight_number(cursor.getString("Flight.flight_number"));
            planeTicket.setPrice(cursor.getInt("Plane_Ticket.price"));
            planeTicket.setShipping_space(cursor.getString("Plane_Ticket.shipping_space"));
            planeTicket.setState(cursor.getString("Plane_Ticket.state"));
            planeTicket.setTakeoff_time(date);
            objects[0] = flight;
            objects[1] = planeTicket;
            list.add(objects);
        }
        closeDatabase();
        return list;
    }

    @Override
    public Object[] GetFlightsByPT(String plane_ticket_number) throws SQLException {
        Object[] objects;
        getDatabase();
        String sql = "SELECT Flight.flight_number, Flight.is_domestic, Flight.takeoff_city, Flight.landing_city, Flight.transit_city, Flight.takeoff_time, Flight.punctuality_rate, Flight.is_direct_flight, Flight.is_share, Flight.time_period, Flight.airline_company, Flight.food, Flight.departure_terminal, Flight.landing_terminal, Plane_Ticket.plane_ticket_number, Plane_Ticket.price, Plane_Ticket.shipping_space, Plane_Ticket.state " +
                "FROM Flight,Plane_Ticket " +
                "WHERE Plane_Ticket.plane_ticket_number = ?" +
                " and Flight.flight_number = Plane_Ticket.flight_number" +
                " and Flight.takeoff_time = Plane_Ticket.takeoff_time";
        ResultSet rs = null;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,plane_ticket_number);
        rs = preparedStatement.executeQuery();
//        Cursor cursor = db.rawQuery("SELECT Flight.flight_number, Flight.is_domestic, Flight.takeoff_city, Flight.landing_city, Flight.transit_city, Flight.takeoff_time, Flight.punctuality_rate, Flight.is_direct_flight, Flight.is_share, Flight.time_period, Flight.airline_company, Flight.food, Flight.departure_terminal, Flight.landing_terminal, Plane_Ticket.plane_ticket_number, Plane_Ticket.price, Plane_Ticket.shipping_space, Plane_Ticket.state " +
//                        "FROM Flight,Plane_Ticket " +
//                        "WHERE Plane_Ticket.plane_ticket_number = ?" +
//                        " and Flight.flight_number = Plane_Ticket.flight_number" +
//                        " and Flight.takeoff_time = Plane_Ticket.takeoff_time"
//                ,new String[]{plane_ticket_number});
        Flight flight = null;
        PlaneTicket planeTicket = null;
        if(!rs.next()){
            return null;
        }
        rs.beforeFirst();
        rs.next();
        List<Object[]> list= new ArrayList<Object[]>();
        objects = new Object[2];
        flight = new Flight();
        planeTicket = new PlaneTicket();
        flight.setFlight_number(rs.getString("Flight.flight_number"));
        flight.setIs_domestic(rs.getInt("Flight.is_domestic"));
        flight.setTakeoff_city(rs.getString("Flight.takeoff_city"));
        flight.setLanding_city(rs.getString("Flight.landing_city"));
        flight.setTransit_city(rs.getString("Flight.transit_city"));
        Date date = null;
        String str = rs.getString("Flight.takeoff_time");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = (Date)format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flight.setTakeoff_time(date);
        flight.setPunctuality_rate(rs.getDouble("Flight.punctuality_rate"));
        flight.setIs_direct_flight(rs.getInt("Flight.is_direct_flight"));
        flight.setIs_share(rs.getInt("Flight.is_share"));
        flight.setTime_period(rs.getString("Flight.time_period"));
        flight.setAirline_company(rs.getString("Flight.airline_company"));
        flight.setFood(rs.getInt("Flight.food"));
        flight.setDeparture_terminal(rs.getString("Flight.departure_terminal"));
        flight.setLanding_terminal(rs.getString("Flight.landing_terminal"));

        planeTicket.setPlane_ticket_number(rs.getInt("Plane_Ticket.plane_ticket_number"));
        planeTicket.setFlight_number(rs.getString("Flight.flight_number"));
        planeTicket.setPrice(rs.getInt("Plane_Ticket.price"));
        planeTicket.setShipping_space(rs.getString("Plane_Ticket.shipping_space"));
        planeTicket.setState(rs.getString("Plane_Ticket.state"));
        planeTicket.setTakeoff_time(date);
        objects[0] = flight;
        objects[1] = planeTicket;

        rs.close();
        return objects;
    }
}
