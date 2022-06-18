package com.sapphireStar.entity;

import java.io.Serializable;
import java.util.Objects;

public class Adminstrator implements Serializable {

    private static final long serialVersionUID = 2097456524364746460L;
    private String phone;
    private String id;

    public Adminstrator() {
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

    public Adminstrator(String phone, String id) {
        this.phone = phone;
        this.id = id;
    }
    @Override
    public int hashCode(){
        return Integer.parseInt(this.phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adminstrator that = (Adminstrator) o;
        return Objects.equals(phone, that.phone) && Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "Adminstrator{" +
                "phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
