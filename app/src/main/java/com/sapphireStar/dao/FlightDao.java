package com.sapphireStar.dao;

import com.sapphireStar.entity.Flight;

import java.util.Date;
import java.util.List;

public interface FlightDao {
    List<Flight> GetFlights(String dateFind, String takeoff_city, String landing_city);
}
