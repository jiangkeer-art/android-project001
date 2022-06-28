package com.sapphireStar.dao;

import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyAttention;
import com.sapphireStar.entity.PlaneTicket;

import java.sql.SQLException;
import java.util.List;

public interface MyAttentionDao {
    List<Object[]> getMyAttention(String phone) throws SQLException;
    void addMyAttention(int plane_ticket_number,String phone) throws SQLException;
    void removeMyAttention(int plane_ticket_number,String phone) throws SQLException;
}
