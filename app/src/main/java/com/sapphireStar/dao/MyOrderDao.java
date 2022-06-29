package com.sapphireStar.dao;

import com.sapphireStar.entity.MyOrder;

import java.sql.SQLException;
import java.util.List;

public interface MyOrderDao {
    List<Object[]> getMyOrderByPhone(String phone,String state) throws SQLException;
    void addMyOrder(int plane_ticket_number,String phone,int order_number,int state) throws SQLException;
    void removeMyOrder(int plane_ticket_number,String phone) throws SQLException;
}
