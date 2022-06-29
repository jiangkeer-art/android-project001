package com.sapphireStar.android_project.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapphireStar.android_project.MineActivity.Change_Password;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.FlightDaoImpl;
import com.sapphireStar.dao.impl.MyAttentionDaoImpl;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyAttention;
import com.sapphireStar.entity.PlaneTicket;
import com.sapphireStar.util.CommonDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    public String takeoff_time="",takeoff_city="",landing_city="",eco="",bus="",direct="",share="",domestic="",phone="";
    public int is_eco=0,is_bus=0,is_direct=0,is_share=0,is_domestic=0;
    public List<Flight> flightList = new ArrayList<>();
    public List<PlaneTicket> planeTicketList = new ArrayList<>();
    public List<PlaneTicket> myAttentionsPlaneTicketList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        try {
            initFlight();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = findViewById(R.id.recycle_view_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        FlightAdapter adapter = new FlightAdapter(flightList,planeTicketList,SearchActivity.this,myAttentionsPlaneTicketList,phone);
        recyclerView.setAdapter(adapter);
    }

    private void initFlight() throws SQLException {
        takeoff_city = getIntent().getStringExtra("takeoff_city");
        takeoff_time = getIntent().getStringExtra("takeoff_time");
        landing_city = getIntent().getStringExtra("landing_city");
        phone = getIntent().getStringExtra("phone");
        eco = getIntent().getStringExtra("is_eco");
        bus = getIntent().getStringExtra("is_bus");
        direct = getIntent().getStringExtra("is_direct");
        share = getIntent().getStringExtra("is_share");
        domestic = getIntent().getStringExtra("is_domestic");
        is_eco = Integer.valueOf(eco).intValue();
        is_bus = Integer.valueOf(bus).intValue();
        is_direct = Integer.valueOf(direct).intValue();
        is_share = Integer.valueOf(share).intValue();
        is_domestic = Integer.valueOf(domestic).intValue();
        Log.d("test",takeoff_time+takeoff_city+landing_city+is_domestic+is_direct+is_eco+is_bus+is_share );

        CommonDB db = new CommonDB();
        SQLiteDatabase sqlite = db.getSqliteObject(SearchActivity.this,"FlightDataBase.db");
        FlightDao flightDao = new FlightDaoImpl(sqlite);
        List<Object[]> list = flightDao.GetFlights(takeoff_time,takeoff_city,landing_city,is_domestic,is_direct,is_eco,is_bus,is_share);
        Log.d("test", String.valueOf(list.size()));
        Object[] objects = list.get(0);
        Log.d("test", objects[0].getClass().getSimpleName());
        ObjectMapper objectMapper = new ObjectMapper();
        Flight flight = objectMapper.convertValue(objects[0], Flight.class);
        flightList.add(flight);
        PlaneTicket planeTicket = objectMapper.convertValue(objects[1], PlaneTicket.class);
        planeTicketList.add(planeTicket);

        MyAttentionDao myAttention = new MyAttentionDaoImpl(sqlite);
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
                myAttentionsPlaneTicketList.add(myAttentionPlaneTicket);
            }
        }

    }
}