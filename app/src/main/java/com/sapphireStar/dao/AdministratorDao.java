package com.sapphireStar.dao;

import com.sapphireStar.entity.Administrator;

import java.sql.SQLException;

public interface AdministratorDao {
    Administrator getAdministratorByPhone(String phone) throws SQLException;
}
