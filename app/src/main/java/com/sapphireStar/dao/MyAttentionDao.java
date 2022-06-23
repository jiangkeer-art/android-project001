package com.sapphireStar.dao;

import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyAttention;
import com.sapphireStar.entity.PlaneTicket;

import java.util.List;

public interface MyAttentionDao {
    List<MyAttention> getMyAttention(String phone);
    Object[] addMyAttention(Flight flight, PlaneTicket planeTicket,String phone);
    void removeMyAttention(Flight flight, PlaneTicket planeTicket,String phone);
}
