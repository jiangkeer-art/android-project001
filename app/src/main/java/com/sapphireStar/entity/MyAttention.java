package com.sapphireStar.entity;

import java.io.Serializable;
import java.util.Objects;

public class MyAttention implements Serializable {
    private static final long serialVersionUID = 4939116254166401673L;
    private int plane_ticket_number;
    private String phone;

    public MyAttention() {
    }

    public int getPlane_ticket_number() {
        return plane_ticket_number;
    }

    public void setPlane_ticket_number(int plane_ticket_number) {
        this.plane_ticket_number = plane_ticket_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MyAttention(int plane_ticket_number, String phone) {
        this.plane_ticket_number = plane_ticket_number;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyAttention that = (MyAttention) o;
        return plane_ticket_number == that.plane_ticket_number && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plane_ticket_number, phone);
    }

    @Override
    public String toString() {
        return "MyAttention{" +
                "plane_ticket_number=" + plane_ticket_number +
                ", phone='" + phone + '\'' +
                '}';
    }
}
