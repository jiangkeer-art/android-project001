package com.sapphireStar.dao;

import com.sapphireStar.entity.MyOrder;

import java.sql.SQLException;
import java.util.List;

public interface MyOrderDao {
    List<Object[]> getMyOrderByPhone(String phone) throws SQLException;
}
