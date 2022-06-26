package com.sapphireStar.android_project.OrderActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapphireStar.android_project.R;
import com.sapphireStar.android_project.SearchActivity.FlightAdapter;
import com.sapphireStar.android_project.SearchActivity.SearchActivity;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.dao.impl.MyOrderDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.util.CommonDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    public List<Flight> flightList = new ArrayList<>();
    public String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        try {
            initFlight();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = findViewById(R.id.recycle_view_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        OrderAdapter adapter = new OrderAdapter(flightList);
        recyclerView.setAdapter(adapter);
    }

    private void initFlight() throws SQLException {
        CommonDB db = new CommonDB();
        SQLiteDatabase sqLite = db.getSqliteObject(OrderActivity.this,"FlightDataBase.db");
        MyOrderDao orderDao = new MyOrderDaoImpl(sqLite);
        phone = getIntent().getStringExtra("phone");
        Log.d("test", phone);
        List<Object[]> list = orderDao.getMyOrderByPhone(phone);
        if(list.size()>0) {
            Object[] objects = list.get(0);
            ObjectMapper objectMapper = new ObjectMapper();
            Flight flight = objectMapper.convertValue(objects[0], Flight.class);
            flightList.add(flight);
        }
    }
}