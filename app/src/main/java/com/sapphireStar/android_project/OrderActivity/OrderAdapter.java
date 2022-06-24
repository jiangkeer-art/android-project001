package com.sapphireStar.android_project.OrderActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sapphireStar.android_project.R;
import com.sapphireStar.entity.Flight;

import java.util.Arrays;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Flight> mFlightList;

    public OrderAdapter(List<Flight> flightList){
        mFlightList=flightList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Flight flight = mFlightList.get(position);
        holder.flight_number.setText(flight.getFlight_number());
        holder.air_company.setText(flight.getAirline_company());
        String takeoff_time_string = flight.getTakeoff_time().toString();
        char[] time = takeoff_time_string.toCharArray();
        char[] time2 = Arrays.copyOfRange(time,11,16);
        takeoff_time_string = new String(time2);
        holder.takeoff_time.setText(takeoff_time_string);
        holder.departure_terminal.setText(flight.getDeparture_terminal());
        holder.landing_terminal.setText(flight.getLanding_terminal());

        String ssm,all;
        StringBuffer h =new StringBuffer();
        StringBuffer m =new StringBuffer();
        h.insert(0,time2[0]);
        h.insert(1,time2[1]);
        m.insert(0,time2[3]);
        m.insert(1,time2[4]);
        int sh = Integer.parseInt(h.toString());
        int PT = Integer.valueOf(flight.getTime_period()).intValue();
        sh += PT/60;
        String ssh = String.valueOf(sh);
        int sm = Integer.parseInt(m.toString());
        sm += PT % 60;
        if(sm<10){
            ssm = String.valueOf(sm);
            all = ssh +":"+ "0"+ssm ;
        }else {
            ssm = String.valueOf(sm);
            all = ssh + ":" + ssm;
        }
        holder.landing_time.setText(all);
    }

    @Override
    public int getItemCount() {
        return mFlightList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView flight_number,air_company,takeoff_time,departure_terminal,landing_terminal,landing_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flight_number = itemView.findViewById(R.id.flight_number);
            air_company = itemView.findViewById(R.id.air_company);
            takeoff_time = itemView.findViewById(R.id.takeoff_time);
            departure_terminal = itemView.findViewById(R.id.departure_terminal);
            landing_terminal = itemView.findViewById(R.id.landing_terminal);
            landing_time = itemView.findViewById(R.id.landing_time);
        }
    }
}
