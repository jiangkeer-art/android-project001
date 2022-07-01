package com.sapphireStar.dao;

import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.entity.User;

import java.sql.SQLException;

public interface NormalUserDao {
    NormalUser getUserByPhone(String phone) throws SQLException;
    int addUser(User user) throws SQLException;
    int addNormalUSer(NormalUser normalUser) throws SQLException;
    int modifyPassword(String phone, String newPassword,String reNewPassword,String oldPassword) throws SQLException;
    Object Login(String phone,String password) throws SQLException;
    int modifyPhone(String oldPhone,String newPhone,String password) throws SQLException;
    int modifyIdNumber(String phone,String password,String IdNumber,String name) throws SQLException;
    int forgetPassword(String phone,String password,String rePassword) throws SQLException;
}
