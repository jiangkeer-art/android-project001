package com.sapphireStar.dao;

import com.sapphireStar.entity.PlaneTicket;

import java.util.List;

public interface PlaneTicketDao {
    List<PlaneTicket> getPlaneTicketByFlight(String flight);
}
