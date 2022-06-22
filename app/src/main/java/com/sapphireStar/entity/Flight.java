package com.sapphireStar.entity;

/*import com.fasterxml.jackson.annotation.JsonFormat;*/

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Flight implements Serializable {
    private static final long serialVersionUID = 9144588912636223266L;

    private String flight_number;
    private boolean is_domestic;
    private String takeoff_city;
/*    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")*/
    private Date takeoff_time;
    private double punctuality_rate;
    private boolean is_direct_flight;
    private boolean is_share;
    private boolean food;
    private String time_period;
    private String airline_company;
    private String departure_terminal;
    private String landing_terminal;

    public Flight(String flight_number, boolean is_domestic, String takeoff_city, Date takeoff_time, double punctuality_rate, boolean is_direct_flight, boolean is_share, boolean food, String time_period, String airline_company, String departure_terminal, String landing_terminal) {
        this.flight_number = flight_number;
        this.is_domestic = is_domestic;
        this.takeoff_city = takeoff_city;
        this.takeoff_time = takeoff_time;
        this.punctuality_rate = punctuality_rate;
        this.is_direct_flight = is_direct_flight;
        this.is_share = is_share;
        this.food = food;
        this.time_period = time_period;
        this.airline_company = airline_company;
        this.departure_terminal = departure_terminal;
        this.landing_terminal = landing_terminal;
    }
    public Flight() {
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public boolean isIs_domestic() {
        return is_domestic;
    }

    public void setIs_domestic(boolean is_domestic) {
        this.is_domestic = is_domestic;
    }

    public String getTakeoff_city() {
        return takeoff_city;
    }

    public void setTakeoff_city(String takeoff_city) {
        this.takeoff_city = takeoff_city;
    }

    public Date getTakeoff_time() {
        return takeoff_time;
    }

    public void setTakeoff_time(Date takeoff_time) {
        this.takeoff_time = takeoff_time;
    }

    public double getPunctuality_rate() {
        return punctuality_rate;
    }

    public void setPunctuality_rate(double punctuality_rate) {
        this.punctuality_rate = punctuality_rate;
    }

    public boolean isIs_direct_flight() {
        return is_direct_flight;
    }

    public void setIs_direct_flight(boolean is_direct_flight) {
        this.is_direct_flight = is_direct_flight;
    }

    public boolean isIs_share() {
        return is_share;
    }

    public void setIs_share(boolean is_share) {
        this.is_share = is_share;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public String getTime_period() {
        return time_period;
    }

    public void setTime_period(String time_period) {
        this.time_period = time_period;
    }

    public String getAirline_company() {
        return airline_company;
    }

    public void setAirline_company(String airline_company) {
        this.airline_company = airline_company;
    }

    public String getDeparture_terminal() {
        return departure_terminal;
    }

    public void setDeparture_terminal(String departure_terminal) {
        this.departure_terminal = departure_terminal;
    }

    public String getLanding_terminal() {
        return landing_terminal;
    }

    public void setLanding_terminal(String landing_terminal) {
        this.landing_terminal = landing_terminal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return is_domestic == flight.is_domestic && Double.compare(flight.punctuality_rate, punctuality_rate) == 0 && is_direct_flight == flight.is_direct_flight && is_share == flight.is_share && food == flight.food && Objects.equals(flight_number, flight.flight_number) && Objects.equals(takeoff_city, flight.takeoff_city) && Objects.equals(takeoff_time, flight.takeoff_time) && Objects.equals(time_period, flight.time_period) && Objects.equals(airline_company, flight.airline_company) && Objects.equals(departure_terminal, flight.departure_terminal) && Objects.equals(landing_terminal, flight.landing_terminal);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(this.flight_number);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flight_number='" + flight_number + '\'' +
                ", is_domestic=" + is_domestic +
                ", takeoff_city='" + takeoff_city + '\'' +
                ", takeoff_time=" + takeoff_time +
                ", punctuality_rate=" + punctuality_rate +
                ", is_direct_flight=" + is_direct_flight +
                ", is_share=" + is_share +
                ", food=" + food +
                ", time_period='" + time_period + '\'' +
                ", airline_company='" + airline_company + '\'' +
                ", departure_terminal='" + departure_terminal + '\'' +
                ", landing_terminal='" + landing_terminal + '\'' +
                '}';
    }
}
