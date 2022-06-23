package com.sapphireStar.dao;

import com.sapphireStar.entity.MyAttention;

import java.util.List;

public interface MyAttentionDao {
    List<MyAttention> getMyAttention(String phone);
}
