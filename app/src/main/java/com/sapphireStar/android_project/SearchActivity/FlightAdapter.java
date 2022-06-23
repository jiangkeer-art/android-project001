package com.sapphireStar.android_project.SearchActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sapphireStar.android_project.R;
import com.sapphireStar.entity.Flight;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private List<Flight> mFlightList;

    public FlightAdapter(List<Flight> flightList){
        mFlightList=flightList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Flight flight = mFlightList.get(position);
        holder.flight_number.setText(flight.getFlight_number());
        holder.air_company.setText(flight.getAirline_company());
        String takeoff_time_string = flight.getTakeoff_time().toString();
        char[] time = takeoff_time_string.toCharArray();
        char[] time2 = Arrays.copyOfRange(time,11,16);
        holder.takeoff_time.setText(Arrays.toString(time2));
        holder.departure_terminal.setText(flight.getDeparture_terminal());
        holder.landing_terminal.setText(flight.getLanding_terminal());
    }

    @Override
    public int getItemCount() {
        return mFlightList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView flight_number,air_company,takeoff_time,departure_terminal,landing_terminal;
        public ViewHolder(View itemView) {
            super(itemView);
            flight_number = itemView.findViewById(R.id.flight_number);
            air_company = itemView.findViewById(R.id.air_company);
            takeoff_time = itemView.findViewById(R.id.takeoff_time);
            departure_terminal = itemView.findViewById(R.id.departure_terminal);
            landing_terminal = itemView.findViewById(R.id.landing_terminal);
        }
    }
}
