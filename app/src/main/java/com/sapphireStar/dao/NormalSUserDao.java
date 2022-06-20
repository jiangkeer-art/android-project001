package com.sapphireStar.dao;

import com.sapphireStar.entity.NormalUser;

public interface NormalSUserDao {
    NormalUser getUserByPhone(String phone);
}
