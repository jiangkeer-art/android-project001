package com.sapphireStar.android_project.MineActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.dao.impl.FlightDaoImpl;
import com.sapphireStar.dao.impl.MyAttentionDaoImpl;
import com.sapphireStar.dao.impl.MyOrderDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyOrder;
import com.sapphireStar.entity.PlaneTicket;
import com.sapphireStar.util.CommonDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {
    public String phone="",state = "",id="";
    public List<Flight> flightList = new ArrayList<>();
    public List<PlaneTicket> planeTicketList = new ArrayList<>();
    public List<MyOrder> myOrder = new ArrayList<>();
    private ImageButton back_to_mine;
    public List<PlaneTicket> myAttentionsPlaneTicketList = new ArrayList<>();
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
        MyOrderAdapter adapter = new MyOrderAdapter(flightList,planeTicketList, MyOrderActivity.this,myOrder,phone,state,myAttentionsPlaneTicketList);
        recyclerView.setAdapter(adapter);
        back_to_mine = findViewById(R.id.back_to_mine);
        back_to_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MyOrderActivity.this, FunctionActivity.class);
                intent.putExtra("phone",phone);
                intent.putExtra("id",id);
                intent.putExtra("frag",1);
                startActivity(intent);
            }
        });
    }

    private void initOrder() throws SQLException {
        //Toast.makeText(MyOrderActivity.this, " fgjfjjhghjghjgjh", Toast.LENGTH_SHORT).show();
        phone = getIntent().getStringExtra("phone");
        id = getIntent().getStringExtra("id");
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
            MyOrder order;
            for(int i=0;i<list1.size();i++) {
                objects1 = list1.get(i);
                myOrderTicket = objectMapper.convertValue(objects1[1], PlaneTicket.class);
                flight = objectMapper.convertValue(objects1[0],Flight.class);
                order = objectMapper.convertValue(objects1[2],MyOrder.class);
                flightList.add(flight);
                planeTicketList.add(myOrderTicket);
                myOrder.add(order);
            }
        }

        MyAttentionDao myAttention = new MyAttentionDaoImpl(sqlite);
        if(myAttention.getMyAttention(phone)==null){
            myAttentionsPlaneTicketList=null;
        }
        else{
            List<Object[]> list2 = myAttention.getMyAttention(phone);
            Object[] objects1;
            PlaneTicket myAttentionPlaneTicket;
            for(int i=0;i<list2.size();i++) {
                objects1 = list2.get(i);
                myAttentionPlaneTicket = objectMapper.convertValue(objects1[1], PlaneTicket.class);
                myAttentionsPlaneTicketList.add(myAttentionPlaneTicket);
            }
        }
    }
}