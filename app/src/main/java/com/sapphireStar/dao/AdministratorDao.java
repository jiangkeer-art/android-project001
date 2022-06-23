package com.sapphireStar.dao;

import com.sapphireStar.entity.Administrator;

public interface AdministratorDao {
    Administrator getAdministratorByPhone(String phone);
}
