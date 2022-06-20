package com.sapphireStar.dao;

import com.sapphireStar.entity.NormalUser;

public interface NormalUserDao {
    NormalUser getUserByPhone(String phone);
}
