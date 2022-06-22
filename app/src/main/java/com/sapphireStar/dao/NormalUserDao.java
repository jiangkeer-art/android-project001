package com.sapphireStar.dao;

import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.entity.User;

public interface NormalUserDao {
    NormalUser getUserByPhone(String phone);
    int addUser(User user);
}
