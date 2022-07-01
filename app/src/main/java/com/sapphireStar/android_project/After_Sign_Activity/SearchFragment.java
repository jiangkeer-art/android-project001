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
    public Button search_buttonF;
    public TextView place1F,place2F,dayF;
    public String takeoff_timeF="",takeoff_cityF="大连",landing_cityF="巴黎";
    public RadioButton is_ecoF,is_busF;
    public String ecoF="0",busF="0",directF="0",shareF="0",domesticF="1";
    public CheckBox is_directF,is_shareF;
    private Button dalian,jixi,changchun,beijing,chengdu,chongqing,shijiazhuang,tianjin,xian;
    private Button dalianF,baliF,lundunF,niuyueF,chengduF,chongqingF,dongjingF,tianjinF,xianF;
    private Integer place1_selection,place2_selection,place1_selectionF,place2_selectionF;
    private String str_place;
    private int year,month,day_of_month;
    private Calendar mCalendar;
    public String phone="",id="";

    //城市选择底部上滑窗口
    private View city_selection,city_selectionF;
    private BottomSheetDialog bottomSheetDialog,bottomSheetDialogF;

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

        //初始化国内Tab内的组件
        place1 =view.findViewById(R.id.place1);
        place2 = view.findViewById(R.id.place2);
        day = view.findViewById(R.id.day);
        is_direct = view.findViewById(R.id.is_direct);
        is_share = view.findViewById(R.id.is_share);
        is_eco = view.findViewById(R.id.is_eco);
        is_bus = view.findViewById(R.id.is_bus);
        search_button = view.findViewById(R.id.search_button);
        //为选择城市弹窗绑定布局
        city_selection = LayoutInflater.from(getActivity()).inflate(R.layout.city_selection,null);
        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(city_selection);

        dalian = city_selection.findViewById(R.id.大连);
        jixi = city_selection.findViewById(R.id.鸡西);
        changchun = city_selection.findViewById(R.id.长春);
        beijing = city_selection.findViewById(R.id.北京);
        chengdu = city_selection.findViewById(R.id.成都);
        chongqing = city_selection.findViewById(R.id.重庆);
        shijiazhuang = city_selection.findViewById(R.id.石家庄);
        tianjin = city_selection.findViewById(R.id.天津);
        xian = city_selection.findViewById(R.id.西安);

        //初始化国际Tab内的组件
        place1F =view.findViewById(R.id.place1F);
        place2F = view.findViewById(R.id.place2F);
        dayF = view.findViewById(R.id.dayF);
        is_directF = view.findViewById(R.id.is_directF);
        is_shareF = view.findViewById(R.id.is_shareF);
        is_ecoF = view.findViewById(R.id.is_ecoF);
        is_busF = view.findViewById(R.id.is_busF);
        search_buttonF = view.findViewById(R.id.search_buttonF);
        //为选择城市弹窗绑定布局
        city_selectionF = LayoutInflater.from(getActivity()).inflate(R.layout.foreign_city_selection,null);
        bottomSheetDialogF = new BottomSheetDialog(getActivity());
        bottomSheetDialogF.setContentView(city_selectionF);

        dalianF = city_selectionF.findViewById(R.id.大连F);
        baliF = city_selectionF.findViewById(R.id.巴黎F);
        lundunF = city_selectionF.findViewById(R.id.伦敦F);
        niuyueF = city_selectionF.findViewById(R.id.纽约F);
        chengduF = city_selectionF.findViewById(R.id.成都F);
        chongqingF = city_selectionF.findViewById(R.id.重庆F);
        dongjingF = city_selectionF.findViewById(R.id.东京F);
        tianjinF = city_selectionF.findViewById(R.id.天津F);
        xianF = city_selectionF.findViewById(R.id.西安F);

        //为国内tab组件绑定监听事件
        OnClick onClick = new OnClick();
        search_button.setOnClickListener(onClick);
        is_eco.setOnClickListener(onClick);
        is_bus.setOnClickListener(onClick);
        is_direct.setOnClickListener(onClick);
        is_share.setOnClickListener(onClick);
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

        //为国际tab组件绑定监听事件
        search_buttonF.setOnClickListener(onClick);
        is_ecoF.setOnClickListener(onClick);
        is_busF.setOnClickListener(onClick);
        is_directF.setOnClickListener(onClick);
        is_shareF.setOnClickListener(onClick);
        dayF.setOnClickListener(onClick);
        place1F.setOnClickListener(onClick);
        place2F.setOnClickListener(onClick);
        dalianF.setOnClickListener(onClick);
        baliF.setOnClickListener(onClick);
        lundunF.setOnClickListener(onClick);
        niuyueF.setOnClickListener(onClick);
        chengduF.setOnClickListener(onClick);
        chongqingF.setOnClickListener(onClick);
        dongjingF.setOnClickListener(onClick);
        tianjinF.setOnClickListener(onClick);
        xianF.setOnClickListener(onClick);
        place1_selectionF=0;
        place2_selectionF=0;
        return view;
    }


    private class OnClick implements View.OnClickListener{
        public String is_adm="";
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
                    id = getActivity().getIntent().getStringExtra("id");
                    is_adm = getActivity().getIntent().getStringExtra("adm");
                    intent.putExtra("adm",is_adm);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",id);
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



                case R.id.place1F:
                    place1_selectionF = 1;
                    bottomSheetDialogF.show();
                    break;
                case R.id.place2F:
                    place2_selectionF = 1;
                    bottomSheetDialogF.show();
                    break;
                case R.id.search_buttonF:
                    intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("takeoff_city",takeoff_cityF);
                    intent.putExtra("landing_city",landing_cityF);
                    intent.putExtra("takeoff_time",takeoff_timeF);
                    intent.putExtra("is_eco",ecoF);
                    intent.putExtra("is_bus",busF);
                    intent.putExtra("is_direct",directF);
                    intent.putExtra("is_share",shareF);
                    intent.putExtra("is_domestic",domesticF);
                    phone = getActivity().getIntent().getStringExtra("phone");
                    id = getActivity().getIntent().getStringExtra("id");
                    is_adm = getActivity().getIntent().getStringExtra("adm");
                    intent.putExtra("adm",is_adm);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",id);
                    //Toast.makeText(getActivity(), takeoff_city+landing_city+takeoff_time+ eco+bus+direct+share+domestic, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    break;
                case R.id.is_ecoF:
                    ecoF="1";
                    busF="0";
                    break;
                case R.id.is_busF:
                    busF="1";
                    ecoF="0";
                    break;
                case R.id.is_directF:
                    directF="1";
                    break;
                case R.id.is_shareF:
                    shareF="1";
                    break;
                case R.id.dayF:
                    mCalendar = Calendar.getInstance(Locale.CHINA);
                    year = mCalendar.get(Calendar.YEAR);
                    month = mCalendar.get(Calendar.MONTH);
                    day_of_month = mCalendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog pickerDialogF = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String date = String.format("%d-%d-%d", year, month+1, dayOfMonth);
                                    dayF.setText(date);
                                    dayF.setTextColor(Color.parseColor("#000000"));
                                    takeoff_timeF = date;
                                }
                            }, year, month, day_of_month);
                    pickerDialogF.show();
                    break;
                case R.id.大连F:
                    str_place = "大连";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.巴黎F:
                    str_place = "巴黎";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.伦敦F:
                    str_place = "伦敦";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.纽约F:
                    str_place = "纽约";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.成都F:
                    str_place = "成都";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.重庆F:
                    str_place = "重庆";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.东京F:
                    str_place = "东京";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.天津F:
                    str_place = "天津";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
                case R.id.西安F:
                    str_place = "西安";
                    if (place1_selectionF==1){
                        takeoff_cityF = str_place;
                        place1F.setText(str_place);
                        place1_selectionF = 0;
                    }else{
                        landing_cityF = str_place;
                        place2F.setText(str_place);
                        place2_selectionF = 0;
                    }
                    bottomSheetDialogF.hide();
                    break;
            }
        }
    }
}