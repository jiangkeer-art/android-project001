package com.sapphireStar.dao;

import com.sapphireStar.entity.Flight;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface FlightDao {
    List<Object[]> GetFlights(String dateFind, String takeoff_city, String landing_city,int is_domestic,int is_direct_flight,int is_eco,int is_business,int is_share) throws SQLException;
    Object[] GetFlightsByPT(String plane_ticket_number) throws SQLException;
    void removeFlight(String flight) throws SQLException;
}
