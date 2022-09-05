package com.sapphireStar.android_project.MineActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.dao.impl.FlightDaoImpl;
import com.sapphireStar.dao.impl.MyAttentionDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.PlaneTicket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyAttention extends AppCompatActivity {
    public String phone="",id="",adm="";
    public List<Flight> flightList = new ArrayList<>();
    public List<PlaneTicket> planeTicketList = new ArrayList<>();
    public List<PlaneTicket> myAttentionsPlaneTicketList = new ArrayList<>();
    private ImageButton back_to_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attention);
        //首先获取数据
        try {
            initAttention();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //获取布局文件中的recycleView控件并设置adapter
        RecyclerView recyclerView = findViewById(R.id.recycle_view_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyAttention.this);
        recyclerView.setLayoutManager(layoutManager);
        MyAttentionAdapter adapter = new MyAttentionAdapter(flightList,planeTicketList,MyAttention.this,myAttentionsPlaneTicketList,phone);
        recyclerView.setAdapter(adapter);
        //返回按钮
        back_to_mine = findViewById(R.id.back_to_mine);
        back_to_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MyAttention.this, FunctionActivity.class);
                intent.putExtra("phone",phone);
                intent.putExtra("id",id);
                intent.putExtra("adm",adm);
                intent.putExtra("frag",1);
                startActivity(intent);
            }
        });
    }

    private void initAttention() throws SQLException {
        //获取当前用户信息
        phone = getIntent().getStringExtra("phone");
        id = getIntent().getStringExtra("id");
        adm = getIntent().getStringExtra("adm");
        //Log.d("testTTTMyA",takeoff_time+takeoff_city+landing_city+is_domestic+is_direct+is_eco+is_bus+is_share );

        ObjectMapper objectMapper = new ObjectMapper();

        //获取MyAttention数据并分别添加到相应的List中
        MyAttentionDao myAttention = new MyAttentionDaoImpl();
        if(myAttention.getMyAttention(phone)==null){
            myAttentionsPlaneTicketList=null;
        }
        else{
            List<Object[]> list1 = myAttention.getMyAttention(phone);
            Object[] objects1;
            PlaneTicket myAttentionPlaneTicket;
            for(int i=0;i<list1.size();i++) {
                objects1 = list1.get(i);
                myAttentionPlaneTicket = objectMapper.convertValue(objects1[1], PlaneTicket.class);
                FlightDao flightDao = new FlightDaoImpl();
                int planeTicketNumber = myAttentionPlaneTicket.getPlane_ticket_number();
                Object[] objects = flightDao.GetFlightsByPT(String.valueOf(planeTicketNumber));
                Flight flight = objectMapper.convertValue(objects[0], Flight.class);
                flightList.add(flight);
                PlaneTicket planeTicket = objectMapper.convertValue(objects[1], PlaneTicket.class);
                planeTicketList.add(planeTicket);
                myAttentionsPlaneTicketList.add(myAttentionPlaneTicket);
            }
        }
    }
}