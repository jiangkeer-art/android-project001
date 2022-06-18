package com.sapphireStar.entity;

import java.io.Serializable;
import java.util.Objects;

public class MyOrder implements Serializable {
    private static final long serialVersionUID = 4412183816192386872L;
    private int order_number;
    private String phone;
    private int plane_ticket_number;
    private String state;

    public MyOrder() {
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getPhone() {
        return phone;
    }

    public int getPlane_ticket_number() {
        return plane_ticket_number;
    }

    public void setPlane_ticket_number(int plane_ticket_number) {
        this.plane_ticket_number = plane_ticket_number;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MyOrder(int order_number, String phone, int plane_ticket_number, String state) {
        this.order_number = order_number;
        this.phone = phone;
        this.plane_ticket_number = plane_ticket_number;
        this.state = state;
    }
    @Override
    public int hashCode(){
        return this.order_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyOrder myOrder = (MyOrder) o;
        return order_number == myOrder.order_number && plane_ticket_number == myOrder.plane_ticket_number && Objects.equals(phone, myOrder.phone) && Objects.equals(state, myOrder.state);
    }

    @Override
    public String toString() {
        return "MyOrder{" +
                "order_number=" + order_number +
                ", phone='" + phone + '\'' +
                ", plane_ticket_number=" + plane_ticket_number +
                ", state='" + state + '\'' +
                '}';
    }
}
