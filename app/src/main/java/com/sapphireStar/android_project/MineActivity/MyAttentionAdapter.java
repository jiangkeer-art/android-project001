package com.sapphireStar.android_project.MineActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.dao.impl.MyAttentionDaoImpl;
import com.sapphireStar.dao.impl.MyOrderDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.PlaneTicket;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MyAttentionAdapter extends RecyclerView.Adapter<MyAttentionAdapter.ViewHolder>{
    private List<Flight> mFlightList;
    private List<PlaneTicket> mPlaneTicket;
    private Context mContext;
    private List<PlaneTicket> mmyAttentions;
    private String mPhone;

    public MyAttentionAdapter(List<Flight> flightList,List<PlaneTicket> planeTicket,Context context,List<PlaneTicket> myAttentions,String phone){
        mContext=context;
        mFlightList=flightList;
        mPlaneTicket=planeTicket;
        mmyAttentions=myAttentions;
        mPhone=phone;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attention_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flight flight = mFlightList.get(position);
        PlaneTicket planeTicket = mPlaneTicket.get(position);

        //用来查找我的关注列表是否为空
        if(mmyAttentions!=null) {
            //Toast.makeText(mContext, "addssdasdasdasda succession", Toast.LENGTH_SHORT).show();
            PlaneTicket planeTicket1;
            for (int i = 0; i < mmyAttentions.size(); i++) {
                planeTicket1 = mmyAttentions.get(i);
                //如果两者一样就证明当前的planeTicket为用户收藏的航班
                if (planeTicket.getPlane_ticket_number() == planeTicket1.getPlane_ticket_number()) {
                    //设置各个控件的显示内容
                    holder.is_attentiond = 1;
                    holder.attention.setImageResource(R.drawable.shoucang2);
                    holder.flight_number.setText(flight.getFlight_number() + "航班");
                    holder.air_company.setText(flight.getAirline_company());
                    String takeoff_time_string = flight.getTakeoff_time().toString();
                    char[] time = takeoff_time_string.toCharArray();
                    char[] time2 = Arrays.copyOfRange(time, 11, 16);
                    takeoff_time_string = new String(time2);
                    holder.takeoff_time.setText(takeoff_time_string);
                    holder.departure_terminal.setText(flight.getDeparture_terminal());
                    holder.landing_terminal.setText(flight.getLanding_terminal());

                    //时间转换
                    String ssm, all;
                    StringBuffer h = new StringBuffer();
                    StringBuffer m = new StringBuffer();
                    h.insert(0, time2[0]);
                    h.insert(1, time2[1]);
                    m.insert(0, time2[3]);
                    m.insert(1, time2[4]);
                    int sh = Integer.parseInt(h.toString());
                    int PT = Integer.valueOf(flight.getTime_period()).intValue();
                    sh += PT / 60;
                    String ssh = String.valueOf(sh);
                    int sm = Integer.parseInt(m.toString());
                    sm += PT % 60;
                    if (sm < 10) {
                        ssm = String.valueOf(sm);
                        all = ssh + ":" + "0" + ssm;
                    } else {
                        ssm = String.valueOf(sm);
                        all = ssh + ":" + ssm;
                    }
                    holder.landing_time.setText(all);

                    if (flight.getIs_direct_flight() == 1) {
                        holder.is_direct.setText(flight.getTransit_city());
                    } else {
                        holder.is_direct.setText("直飞");
                    }
                    if (flight.getIs_share() == 1) {
                        holder.is_share.setText("共享航班");
                    } else
                        holder.is_share.setText("非共享航班");
                    if (flight.getFood() == 1) {
                        holder.food.setText("有餐食");
                    } else
                        holder.food.setText("无餐食");

                    holder.punctuality_rate.setText(String.valueOf((int) flight.getPunctuality_rate()) + "%准点率");
                    holder.time_period.setText("共计" + flight.getTime_period() + "分钟");

                    holder.is_bus.setText(planeTicket.getShipping_space());
                    holder.price.setText("¥" + planeTicket.getPrice());

                    //当点击收藏按钮时可以取消收藏或者从新添加收藏
                    holder.attention.setOnClickListener(new View.OnClickListener() {
                        int ticket_number = planeTicket.getPlane_ticket_number();

                        @Override
                        public void onClick(View v) {
                            if (holder.is_attentiond == 1) {
                                holder.attention.setImageResource(R.drawable.shoucang);
                                holder.is_attentiond = 0;
                                MyAttentionDao myAttention = new MyAttentionDaoImpl();
                                try {
                                    myAttention.removeMyAttention(ticket_number, mPhone);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                holder.attention.setImageResource(R.drawable.shoucang2);
                                holder.is_attentiond = 1;
                                MyAttentionDao myAttention = new MyAttentionDaoImpl();
                                try {
                                    myAttention.addMyAttention(ticket_number, mPhone);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    //设置订票按钮的点击事件
                    holder.order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int ticket_number = planeTicket.getPlane_ticket_number();
                            int order_number = (int) ((Math.random() * 9 + 1) * Math.pow(10, 5));
                            MyOrderDao myOrderDao = new MyOrderDaoImpl();
                            try {
                                myOrderDao.addMyOrder(ticket_number, mPhone, order_number, 0);
                                Toast.makeText(mContext, "add succession", Toast.LENGTH_SHORT).show();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mFlightList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView flight_number,air_company,takeoff_time,departure_terminal,landing_terminal,landing_time,is_direct,is_share,food,punctuality_rate,time_period,is_bus,price;
        public ImageButton attention;
        public Button order;
        public int is_attentiond=0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //设置各个控件
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
