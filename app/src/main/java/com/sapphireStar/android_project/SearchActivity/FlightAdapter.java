package com.sapphireStar.android_project.SearchActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.MineActivity.MyAttention;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.dao.PlaneTicketDao;
import com.sapphireStar.dao.impl.FlightDaoImpl;
import com.sapphireStar.dao.impl.MyAttentionDaoImpl;
import com.sapphireStar.dao.impl.MyOrderDaoImpl;
import com.sapphireStar.dao.impl.PlaneTicketDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyOrder;
import com.sapphireStar.entity.PlaneTicket;
import com.sapphireStar.util.CommonDB;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    private List<Flight> mFlightList;
    private List<PlaneTicket> mPlaneTicket;
    private Context mContext;
    private List<PlaneTicket> mmyAttentions;
    private String mPhone;
    private String is_admm;

    public FlightAdapter(List<Flight> flightList,List<PlaneTicket> planeTicket,Context context,List<PlaneTicket> myAttentions,String phone,String is_adm){
        mContext=context;
        mFlightList=flightList;
        mPlaneTicket=planeTicket;
        mmyAttentions=myAttentions;
        mPhone=phone;
        is_admm=is_adm;
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
        PlaneTicket planeTicket = mPlaneTicket.get(position);



        holder.flight_number.setText(flight.getFlight_number()+"航班");
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

        if(flight.getIs_direct_flight()==1){
            holder.is_direct.setText(flight.getTransit_city());
        }
        else{
            holder.is_direct.setText("直飞");
        }
        if(flight.getIs_share()==1){
            holder.is_share.setText("共享航班");
        }
        else
            holder.is_share.setText("非共享航班");
        if(flight.getFood()==1){
            holder.food.setText("有餐食");
        }else
            holder.food.setText("无餐食");

        holder.punctuality_rate.setText(String.valueOf((int)flight.getPunctuality_rate())+"%准点率");
        holder.time_period.setText("共计"+flight.getTime_period()+"分钟");

        holder.is_bus.setText(planeTicket.getShipping_space());
        holder.price.setText("¥"+planeTicket.getPrice());

        if(mmyAttentions!=null) {
            //Toast.makeText(mContext, "addssdasdasdasda succession", Toast.LENGTH_SHORT).show();
            PlaneTicket planeTicket1;
            for (int i = 0; i < mmyAttentions.size(); i++) {
                planeTicket1=mmyAttentions.get(i);
                if (planeTicket.getPlane_ticket_number() == planeTicket1.getPlane_ticket_number()) {
                    holder.is_attentiond = 1;
                    holder.attention.setImageResource(R.drawable.shoucang2);
                }
            }
        }


        holder.attention.setOnClickListener(new View.OnClickListener() {
            int ticket_number=planeTicket.getPlane_ticket_number();
            @Override
            public void onClick(View v) {
                if(holder.is_attentiond==1){
                    holder.attention.setImageResource(R.drawable.shoucang);
                    holder.is_attentiond=0;
                    CommonDB db = new CommonDB();
                    SQLiteDatabase sqlite = db.getSqliteObject(mContext,"FlightDataBase.db");
                    MyAttentionDao myAttention = new MyAttentionDaoImpl(sqlite);
                    try {
                        myAttention.removeMyAttention(ticket_number,mPhone);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    holder.attention.setImageResource(R.drawable.shoucang2);
                    holder.is_attentiond=1;
                    CommonDB db = new CommonDB();
                    SQLiteDatabase sqlite = db.getSqliteObject(mContext,"FlightDataBase.db");
                    MyAttentionDao myAttention = new MyAttentionDaoImpl(sqlite);
                    try {
                        myAttention.addMyAttention(ticket_number,mPhone);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if(is_admm.equals("0")) {
            holder.order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int ticket_number = planeTicket.getPlane_ticket_number();
                    int order_number = (int) ((Math.random() * 9 + 1) * Math.pow(10, 5));
                    CommonDB db = new CommonDB();
                    SQLiteDatabase sqlite = db.getSqliteObject(mContext, "FlightDataBase.db");
                    MyOrderDao myOrderDao = new MyOrderDaoImpl(sqlite);
                    try {
                        myOrderDao.addMyOrder(ticket_number, mPhone, order_number, 0);
                        Toast.makeText(mContext, "add succession", Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        else if(is_admm.equals("1")){
            holder.order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonDB db = new CommonDB();
                    SQLiteDatabase sqlite = db.getSqliteObject(mContext, "FlightDataBase.db");
                    FlightDao flightDao = new FlightDaoImpl(sqlite);
                    try {
                        flightDao.removeFlight(flight.getFlight_number());
                        Toast.makeText(mContext, "delete succession", Toast.LENGTH_SHORT).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        //Log.d("testtestetst",String.valueOf(mFlightList.size()));
        return mFlightList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView flight_number,air_company,takeoff_time,departure_terminal,landing_terminal,landing_time,is_direct,is_share,food,punctuality_rate,time_period,is_bus,price;
        public ImageButton attention;
        public Button order;
        public int is_attentiond=0;
        public ViewHolder(View itemView) {
            super(itemView);
            flight_number = itemView.findViewById(R.id.flight_number);
            air_company = itemView.findViewById(R.id.air_company);
            takeoff_time = itemView.findViewById(R.id.takeoff_time);
            departure_terminal = itemView.findViewById(R.id.departure_terminal);
            landing_terminal = itemView.findViewById(R.id.landing_terminal);
            landing_time = itemView.findViewById(R.id.landing_time);
            is_direct = itemView.findViewById(R.id.is_direct);
            is_share = itemView.findViewById(R.id.is_share);
            food = itemView.findViewById(R.id.food);
            punctuality_rate = itemView.findViewById(R.id.punctuality_rate);
            time_period = itemView.findViewById(R.id.time_period);
            is_bus = itemView.findViewById(R.id.is_bus);
            price = itemView.findViewById(R.id.price);
            attention = itemView.findViewById(R.id.attention);
            order = itemView.findViewById(R.id.order);
        }
    }
}
