package com.sapphireStar.entity;

/*import com.fasterxml.jackson.annotation.JsonFormat;*/

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PlaneTicket implements Serializable {
    private static final long serialVersionUID = 916401259560976449L;

    private int plane_ticket_number;
    private String flight_number;

/*    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")*/
    private Date takeoff_time;

    private int price;
    private String shipping_space;
    private String state;

    public PlaneTicket(int plane_ticket_number, String flight_number, Date takeoff_time, int price, String shipping_space, String state) {
        this.plane_ticket_number = plane_ticket_number;
        this.flight_number = flight_number;
        this.takeoff_time = takeoff_time;
        this.price = price;
        this.shipping_space = shipping_space;
        this.state = state;
    }

    public PlaneTicket() {
    }

    public int getPlane_ticket_number() {
        return plane_ticket_number;
    }

    public void setPlane_ticket_number(int plane_ticket_number) {
        this.plane_ticket_number = plane_ticket_number;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public Date getTakeoff_time() {
        return takeoff_time;
    }

    public void setTakeoff_time(Date takeoff_time) {
        this.takeoff_time = takeoff_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShipping_space() {
        return shipping_space;
    }

    public void setShipping_space(String shipping_space) {
        this.shipping_space = shipping_space;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaneTicket that = (PlaneTicket) o;
        return plane_ticket_number == that.plane_ticket_number && price == that.price && Objects.equals(flight_number, that.flight_number) && Objects.equals(takeoff_time, that.takeoff_time) && Objects.equals(shipping_space, that.shipping_space) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return this.plane_ticket_number;
    }

    @Override
    public String toString() {
        return "PlaneTicket{" +
                "plane_ticket_number=" + plane_ticket_number +
                ", flight_number='" + flight_number + '\'' +
                ", takeoff_time=" + takeoff_time +
                ", price=" + price +
                ", shipping_space='" + shipping_space + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
