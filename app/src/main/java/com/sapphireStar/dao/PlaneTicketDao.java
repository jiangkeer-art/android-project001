package com.sapphireStar.dao;

import com.sapphireStar.entity.PlaneTicket;

import java.sql.SQLException;
import java.util.List;

public interface PlaneTicketDao {
    List<PlaneTicket> getPlaneTicketByFlight(String flight_number) throws SQLException;

}
