package com.sapphireStar.dao;

import com.sapphireStar.entity.MyOrder;

import java.util.List;

public interface MyOrderDao {
    List<MyOrder> getMyOrderByPhone(String phone);
}