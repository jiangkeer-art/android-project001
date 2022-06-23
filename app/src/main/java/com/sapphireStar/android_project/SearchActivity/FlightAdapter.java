package com.sapphireStar.android_project.SearchActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sapphireStar.android_project.R;
import com.sapphireStar.entity.Flight;

import org.w3c.dom.Text;

import java.util.List;


public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private List<Flight> mFlightList;

    public FlightAdapter(List<Flight> flightlist){
        mFlightList=flightlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Flight flight = mFlightList.get(position);
        holder.text_item.setText(flight.getFlight_number());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView text_item;
        public ViewHolder(View itemView) {
            super(itemView);
            text_item = itemView.findViewById(R.id.text_item);
        }
    }
}
