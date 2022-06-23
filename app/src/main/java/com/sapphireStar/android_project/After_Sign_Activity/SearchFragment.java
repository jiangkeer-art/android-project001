package com.sapphireStar.android_project.After_Sign_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.android_project.SearchActivity.SearchActivity;


public class SearchFragment extends Fragment {

    public Button search_button;
    public EditText place1,place2,day1;
    public String takeoff_time="",takeoff_city="",landing_city="";
    public RadioButton is_eco,is_bus;
    public int eco=0,bus=0,direct=0,share=0,domestic=0;
    public CheckBox is_direct,is_share;
    public Button search_buttonn;
    public EditText place11,place22,day11;
    public String takeoff_timee="",takeoff_cityy="",landing_cityy="";
    public RadioButton is_ecoo,is_buss;
    public int ecoo=0,buss=0,directt=0,sharee=0,domesticc=1;
    public CheckBox is_directt,is_sharee;
    FunctionActivity functionActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        TabHost tab = view.findViewById(android.R.id.tabhost);
        TabHost tab1 = view.findViewById(android.R.id.tabhost);

        //为自定义TabHost样式抓取选项卡布局
        functionActivity = (FunctionActivity) getActivity();
        View domestic = LayoutInflater.from(functionActivity).inflate(R.layout.tabmini,null);
        View international = LayoutInflater.from(functionActivity).inflate(R.layout.tabmini1,null);

        //初始化TabHost容器
        tab.setup();
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        tab.addTab(tab.newTabSpec("国内").setIndicator(domestic).setContent(R.id.国内));
        tab1.addTab(tab.newTabSpec("国际").setIndicator(international).setContent(R.id.国际));

        place1 =view.findViewById(R.id.place1);
        place2 = view.findViewById(R.id.place2);
        day1 = view.findViewById(R.id.day1);

        is_direct = view.findViewById(R.id.is_direct);
        is_share = view.findViewById(R.id.is_share);

        is_eco = view.findViewById(R.id.is_eco);
        is_bus = view.findViewById(R.id.is_bus);
        search_button = view.findViewById(R.id.search_button);
        place11 =view.findViewById(R.id.place11);
        place22 = view.findViewById(R.id.place22);
        day11 = view.findViewById(R.id.day11);

        is_directt = view.findViewById(R.id.is_directt);
        is_sharee = view.findViewById(R.id.is_sharee);

        is_ecoo = view.findViewById(R.id.is_ecoo);
        is_buss = view.findViewById(R.id.is_buss);
        search_buttonn = view.findViewById(R.id.search_buttonn);
        OnClick onClick = new OnClick();
        search_button.setOnClickListener(onClick);
        is_eco.setOnClickListener(onClick);
        is_bus.setOnClickListener(onClick);
        is_direct.setOnClickListener(onClick);
        is_share.setOnClickListener(onClick);
        search_buttonn.setOnClickListener(onClick);
        is_ecoo.setOnClickListener(onClick);
        is_buss.setOnClickListener(onClick);
        is_directt.setOnClickListener(onClick);
        is_sharee.setOnClickListener(onClick);

        return view;
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()){
                case R.id.search_button:
                    takeoff_city = place1.getText().toString();
                    landing_city = place2.getText().toString();
                    takeoff_time = day1.getText().toString();
                    intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("takeoff_city",takeoff_city);
                    intent.putExtra("landing_city",landing_city);
                    intent.putExtra("takeoff_time",takeoff_time);
                    intent.putExtra("is_eco",eco);
                    intent.putExtra("is_bus",bus);
                    intent.putExtra("is_direct",direct);
                    intent.putExtra("is_share",share);
                    intent.putExtra("is_domestic",domestic);
                    Toast.makeText(getActivity(), takeoff_city+landing_city+takeoff_time+ eco+bus+direct+share+domestic, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    break;
                case R.id.is_eco:
                    eco=1;
                    bus=0;
                    break;
                case R.id.is_bus:
                    bus=1;
                    eco=0;
                    break;
                case R.id.is_direct:
                    direct=1;
                    break;
                case R.id.is_share:
                    share=1;
                    break;
                case R.id.search_buttonn:
                    takeoff_cityy = place11.getText().toString();
                    landing_cityy = place22.getText().toString();
                    takeoff_timee = day11.getText().toString();
                    intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("takeoff_city",takeoff_cityy);
                    intent.putExtra("landing_city",landing_cityy);
                    intent.putExtra("takeoff_time",takeoff_timee);
                    intent.putExtra("is_eco",ecoo);
                    intent.putExtra("is_bus",buss);
                    intent.putExtra("is_direct",directt);
                    intent.putExtra("is_share",sharee);
                    intent.putExtra("is_domestic",domesticc);
                    startActivity(intent);
                    break;
                case R.id.is_ecoo:
                    ecoo=1;
                    buss=0;
                    break;
                case R.id.is_buss:
                    buss=1;
                    ecoo=0;
                    break;
                case R.id.is_directt:
                    directt=1;
                    break;
                case R.id.is_sharee:
                    sharee=1;
                    break;
            }
        }
    }
}