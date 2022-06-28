package com.sapphireStar.android_project.After_Sign_Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.android_project.SearchActivity.SearchActivity;

import java.util.Calendar;
import java.util.Locale;


public class SearchFragment extends Fragment{

    public Button search_button;
    public TextView day,place1,place2;
    public String takeoff_time="",takeoff_city="西安",landing_city="大连";
    public RadioButton is_eco,is_bus;
    public String eco="0",bus="0",direct="0",share="0",domestic="0";
    public CheckBox is_direct,is_share;
    public Button search_buttonn;
    public EditText place11,place22,day11;
    public String takeoff_timee="",takeoff_cityy="",landing_cityy="";
    public RadioButton is_ecoo,is_buss;
    public String ecoo="0",buss="0",directt="0",sharee="0",domesticc="1";
    public CheckBox is_directt,is_sharee;
    private Button dalian,jixi,changchun,beijing,chengdu,chongqing,shijiazhuang,tianjin,xian;
    private Integer place1_selection,place2_selection;
    private String str_place;
    private int year,month,day_of_month;
    private Calendar mCalendar;
    public String phone="";

    //城市选择底部上滑窗口
    private View city_selection;
    private BottomSheetDialog bottomSheetDialog;

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

        //为选择城市弹窗绑定布局
        city_selection = LayoutInflater.from(getActivity()).inflate(R.layout.city_selection,null);
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(city_selection);

        place1 =view.findViewById(R.id.place1);
        place2 = view.findViewById(R.id.place2);

        day = view.findViewById(R.id.day);

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

        dalian = city_selection.findViewById(R.id.大连);
        jixi = city_selection.findViewById(R.id.鸡西);
        changchun = city_selection.findViewById(R.id.长春);
        beijing = city_selection.findViewById(R.id.北京);
        chengdu = city_selection.findViewById(R.id.成都);
        chongqing = city_selection.findViewById(R.id.重庆);
        shijiazhuang = city_selection.findViewById(R.id.石家庄);
        tianjin = city_selection.findViewById(R.id.天津);
        xian = city_selection.findViewById(R.id.西安);

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
        day.setOnClickListener(onClick);
        place1.setOnClickListener(onClick);
        place2.setOnClickListener(onClick);

        dalian.setOnClickListener(onClick);
        jixi.setOnClickListener(onClick);
        changchun.setOnClickListener(onClick);
        beijing.setOnClickListener(onClick);
        chengdu.setOnClickListener(onClick);
        chongqing.setOnClickListener(onClick);
        shijiazhuang.setOnClickListener(onClick);
        tianjin.setOnClickListener(onClick);
        xian.setOnClickListener(onClick);

        place1_selection=0;
        place2_selection=0;

        return view;
    }


    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()){
                case R.id.place1:
                    place1_selection = 1;
                    bottomSheetDialog.show();
                    break;
                case R.id.place2:
                    place2_selection = 1;
                    bottomSheetDialog.show();
                    break;
                case R.id.search_button:
                    intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("takeoff_city",takeoff_city);
                    intent.putExtra("landing_city",landing_city);
                    intent.putExtra("takeoff_time",takeoff_time);
                    intent.putExtra("is_eco",eco);
                    intent.putExtra("is_bus",bus);
                    intent.putExtra("is_direct",direct);
                    intent.putExtra("is_share",share);
                    intent.putExtra("is_domestic",domestic);
                    phone = getActivity().getIntent().getStringExtra("phone");
                    intent.putExtra("phone",phone);
                    //Toast.makeText(getActivity(), takeoff_city+landing_city+takeoff_time+ eco+bus+direct+share+domestic, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    break;
                case R.id.is_eco:
                    eco="1";
                    bus="0";
                    break;
                case R.id.is_bus:
                    bus="1";
                    eco="0";
                    break;
                case R.id.is_direct:
                    direct="1";
                    break;
                case R.id.is_share:
                    share="1";
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
                    ecoo="1";
                    buss="0";
                    break;
                case R.id.is_buss:
                    buss="1";
                    ecoo="0";
                    break;
                case R.id.is_directt:
                    directt="1";
                    break;
                case R.id.is_sharee:
                    sharee="1";
                    break;
                case R.id.day:
                    mCalendar = Calendar.getInstance(Locale.CHINA);
                    year = mCalendar.get(Calendar.YEAR);
                    month = mCalendar.get(Calendar.MONTH);
                    day_of_month = mCalendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String date = String.format("%d-%d-%d", year, month+1, dayOfMonth);
                                    day.setText(date);
                                    day.setTextColor(Color.parseColor("#000000"));
                                    takeoff_time = date;
                                }
                            }, year, month, day_of_month);
                    pickerDialog.show();
                    break;
                case R.id.大连:
                    str_place = "大连";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.鸡西:
                    str_place = "鸡西";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.长春:
                    str_place = "长春";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.北京:
                    str_place = "北京";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.成都:
                    str_place = "成都";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.重庆:
                    str_place = "重庆";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.石家庄:
                    str_place = "石家庄";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.天津:
                    str_place = "天津";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
                case R.id.西安:
                    str_place = "西安";
                    if (place1_selection==1){
                        takeoff_city = str_place;
                        place1.setText(str_place);
                        place1_selection = 0;
                    }else{
                        landing_city = str_place;
                        place2.setText(str_place);
                        place2_selection = 0;
                    }
                    bottomSheetDialog.hide();
                    break;
            }
        }
    }
}