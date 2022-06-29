package com.sapphireStar.android_project.MineActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.dao.impl.FlightDaoImpl;
import com.sapphireStar.dao.impl.MyOrderDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.PlaneTicket;
import com.sapphireStar.util.CommonDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {
    public String phone="",state = "";
    public List<Flight> flightList = new ArrayList<>();
    public List<PlaneTicket> planeTicketList = new ArrayList<>();
    public List<PlaneTicket> myOrder = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        try {
            initOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = findViewById(R.id.recycle_view_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyOrderActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        MyOrderAdapter adapter = new MyOrderAdapter(flightList,planeTicketList, MyOrderActivity.this,myOrder,phone,state);
        recyclerView.setAdapter(adapter);
    }

    private void initOrder() throws SQLException {
        //Toast.makeText(MyOrderActivity.this, " fgjfjjhghjghjgjh", Toast.LENGTH_SHORT).show();
        phone = getIntent().getStringExtra("phone");
        state = getIntent().getStringExtra("state");
        //Log.d("testTTTMyA",takeoff_time+takeoff_city+landing_city+is_domestic+is_direct+is_eco+is_bus+is_share );

        CommonDB db = new CommonDB();
        SQLiteDatabase sqlite = db.getSqliteObject(MyOrderActivity.this,"FlightDataBase.db");
        ObjectMapper objectMapper = new ObjectMapper();

        MyOrderDao myOrderDao = new MyOrderDaoImpl(sqlite);
        //Log.d("sqlll",phone + " "+state);
        List<Object[]> list1 = myOrderDao.getMyOrderByPhone(phone,state);
        if(list1.size()==0){
            //Toast.makeText(MyOrderActivity.this, " succession", Toast.LENGTH_SHORT).show();
            myOrder=null;
        }
        else{
            //Toast.makeText(MyOrderActivity.this, "addssdasdasdasda succession", Toast.LENGTH_SHORT).show();
            Object[] objects1;
            PlaneTicket myOrderTicket;
            Flight flight;
            for(int i=0;i<list1.size();i++) {
                objects1 = list1.get(i);
                myOrderTicket = objectMapper.convertValue(objects1[1], PlaneTicket.class);
                flight = objectMapper.convertValue(objects1[0],Flight.class);
                flightList.add(flight);
                planeTicketList.add(myOrderTicket);
                myOrder.add(myOrderTicket);
            }
        }
    }
}