package com.sapphireStar.dao;

import com.sapphireStar.android_project.DataBase.DataBaseHelper;
import com.sapphireStar.entity.PlaneTicket;

import java.sql.SQLException;
import java.util.List;

public interface PlaneTicketDao {
    List<PlaneTicket> getPlaneTicketByFlight(String flight);
    int modifyState(String plane_ticket_number,String takeoff_time) throws SQLException;
}
