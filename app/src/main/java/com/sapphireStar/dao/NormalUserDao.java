package com.sapphireStar.dao;

import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.entity.User;

public interface NormalUserDao {
    NormalUser getUserByPhone(String phone);
    int addUser(User user);
    int addNormalUSer(NormalUser normalUser);
    int modifyPassword(String phone, String newPassword,String reNewPassword,String oldPassword);
    Object Login(String phone,String password);
}
