package com.sapphireStar.android_project.SearchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
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
    public String takeoff_time="",takeoff_city="",landing_city="",eco="",bus="",direct="",share="",domestic="",phone="",id="";
    public int is_eco=0,is_bus=0,is_direct=0,is_share=0,is_domestic=0;
    public List<Flight> flightList = new ArrayList<>();
    public List<PlaneTicket> planeTicketList = new ArrayList<>();
    public List<PlaneTicket> myAttentionsPlaneTicketList = new ArrayList<>();
    private ImageButton back_to_search;
    private TextView float_btn;
    //选择筛选条件底部上滑窗口
    private View filter;
    private BottomSheetDialog bottomSheetDialog;
    private ConditionViewGroup shareGroup,directGroup,sortGroup,seatGroup;
    private TextView confirm;

    private  final ArrayList<String> sharetext = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        try {
            initdata();
            initFlight();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = findViewById(R.id.recycle_view_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        FlightAdapter adapter = new FlightAdapter(flightList,planeTicketList,SearchActivity.this,myAttentionsPlaneTicketList,phone);
        recyclerView.setAdapter(adapter);

        back_to_search = findViewById(R.id.back_to_search);
        back_to_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(SearchActivity.this, FunctionActivity.class);
                intent.putExtra("phone",phone);
                intent.putExtra("id",id);
                intent.putExtra("frag",0);
                startActivity(intent);
            }
        });


        //为选择筛选条件弹窗绑定布局
        filter = LayoutInflater.from(this).inflate(R.layout.filter,null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(filter);

        final ArrayList<String> sharetext = new ArrayList<>();
        sharetext.add("隐藏共享航班");
        sharetext.add("显示共享航班");
        shareGroup = filter.findViewById(R.id.shareGroup);
        shareGroup.addItemViews(sharetext, ConditionViewGroup.TEV_MODE);
        shareGroup.chooseItemStyle(0);
        shareGroup.setGroupClickListener(new ConditionViewGroup.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(int item) {
                if (sharetext.get(item).toString().equals("隐藏共享航班")){
                    is_share = 0;
                }else{
                    is_share = 1;
                }
            }
        });

        final ArrayList<String> directtext = new ArrayList<>();
        directtext.add("仅看直飞航班");
        directtext.add("显示经停航班");
        directGroup = filter.findViewById(R.id.directGroup);
        directGroup.addItemViews(directtext, ConditionViewGroup.TEV_MODE);
        directGroup.chooseItemStyle(0);
        directGroup.setGroupClickListener(new ConditionViewGroup.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(int item) {
                if (directtext.get(item).toString().equals("仅看直飞航班")){
                    is_direct = 1;
                }else{
                    is_direct = 0;
                }
            }
        });

        final ArrayList<String> sorttext = new ArrayList<>();
        sorttext.add("按价格升序");
        sorttext.add("按价格降序");
        sortGroup = filter.findViewById(R.id.sortGroup);
        sortGroup.addItemViews(sorttext, ConditionViewGroup.TEV_MODE);
        sortGroup.chooseItemStyle(0);
        sortGroup.setGroupClickListener(new ConditionViewGroup.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(int item) {
                if (sorttext.get(item).toString().equals("按价格升序")){

                }else{

                }
            }
        });

        final ArrayList<String> seattext = new ArrayList<>();
        seattext.add("只看经济舱");
        seattext.add("只看头等舱");
        seattext.add("无舱位需求");
        seatGroup = filter.findViewById(R.id.seatGroup);
        seatGroup.addItemViews(seattext, ConditionViewGroup.TEV_MODE);
        seatGroup.chooseItemStyle(0);
        seatGroup.setGroupClickListener(new ConditionViewGroup.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(int item) {
                if (seattext.get(item).toString().equals("只看经济舱")){
                    is_eco = 1;
                    is_bus = 0;
                }else if (seattext.get(item).toString().equals("只看头等舱")){
                    is_eco = 0;
                    is_bus = 1;
                }else{
                    is_eco = 0;
                    is_bus = 0;
                }
            }
        });


        float_btn = findViewById(R.id.float_btn);
        float_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });

        //根据筛选条件更新数据集
        confirm = filter.findViewById(R.id.yes);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    initFlight();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initdata(){
        takeoff_city = getIntent().getStringExtra("takeoff_city");
        takeoff_time = getIntent().getStringExtra("takeoff_time");
        landing_city = getIntent().getStringExtra("landing_city");
        phone = getIntent().getStringExtra("phone");
        id = getIntent().getStringExtra("id");
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
    }

    private void initFlight() throws SQLException {
        flightList.clear();
        planeTicketList.clear();
        Log.d("testTTT",takeoff_time+takeoff_city+landing_city+is_domestic+is_direct+is_eco+is_bus+is_share );
        CommonDB db = new CommonDB();
        SQLiteDatabase sqlite = db.getSqliteObject(SearchActivity.this,"FlightDataBase.db");
        FlightDao flightDao = new FlightDaoImpl(sqlite);
        List<Object[]> list = flightDao.GetFlights(takeoff_time,takeoff_city,landing_city,is_domestic,is_direct,is_eco,is_bus,is_share);
        Object[] objects;
        //Log.d("test", objects[0].getClass().getSimpleName());
        ObjectMapper objectMapper = new ObjectMapper();
        if (list==null){
            Toast.makeText(this, "未找到符合条件的航班", Toast.LENGTH_LONG).show();
        }else{
            for(int i=0;i<list.size();i++) {
                Log.d("testtest", String.valueOf(list.size()));
                objects = list.get(i);
                Flight flight = objectMapper.convertValue(objects[0], Flight.class);
                flightList.add(flight);
                PlaneTicket planeTicket = objectMapper.convertValue(objects[1], PlaneTicket.class);
                planeTicketList.add(planeTicket);
            }

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
}