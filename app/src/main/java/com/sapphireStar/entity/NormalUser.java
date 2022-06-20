package com.sapphireStar.entity;

import java.io.Serializable;
import java.util.Objects;

public class NormalUser implements Serializable {
    //版本号
    private static final long serialVersionUID = -8526006766764417777L;
    private String phone;
    private String id;
    private boolean name;
    private String idNumber;

    public NormalUser() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public NormalUser(String phone, String id, boolean name, String idNumber) {
        this.phone = phone;
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
    }
    @Override
    public int hashCode(){
        return Integer.parseInt(this.phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalUser that = (NormalUser) o;
        return name == that.name && Objects.equals(phone, that.phone) && Objects.equals(id, that.id) && Objects.equals(idNumber, that.idNumber);
    }

    @Override
    public String toString() {
        return "NormalUser{" +
                "phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                ", name=" + name +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
