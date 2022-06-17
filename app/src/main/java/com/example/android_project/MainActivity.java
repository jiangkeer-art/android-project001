package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DataBaseHelper(this,"FlightDataBase.dp",null,12);

        //创建数据库并向其中添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        addUserData(db);
        addCityData(db);
        addCompanyData(db);
        addFlightData(db);
        addAdministratorsUserData(db);
        addNormalUserData(db);
        addOrderData(db);
    }

    //添加管理员用户数据函数
    public void addAdministratorsUserData(SQLiteDatabase db){
        ContentValues valueAdministrators_User=new ContentValues();

        //管理员表
        valueAdministrators_User.put("phone","18222279903");
        valueAdministrators_User.put("id","Lord");
        db.insert("Administrators_User",null,valueAdministrators_User);
    }

    //添加普通用户数据函数
    public void addNormalUserData(SQLiteDatabase db){
        ContentValues valueNormal_User=new ContentValues();

        valueNormal_User.put("phone","18333378803");
        valueNormal_User.put("id","FirstTest");
        valueNormal_User.put("name","二蛋");
        valueNormal_User.put("id_number","145685400109835618");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","18777777703");
        valueNormal_User.put("id","SecondTest");
        valueNormal_User.put("name","三蛋");
        valueNormal_User.put("id_number","175686400209635415");
        db.insert("Normal_User",null,valueNormal_User);
    }

    //添加用户数据函数
    public void addUserData(SQLiteDatabase db){
        ContentValues valueUser=new ContentValues();

        valueUser.put("phone","18222279903");
        valueUser.put("password","1215225");
        valueUser.put("is_administrators","1"); //1为管理员，0为普通用户
        db.insert("User",null,valueUser);

        valueUser.put("phone","18333378803");
        valueUser.put("password","123369");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","18777777703");
        valueUser.put("password","321147");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);
    }

    //添加订单数据函数
    public void addOrderData(SQLiteDatabase db){
        ContentValues valueOrderData = new ContentValues();

        valueOrderData.put("phone","18333378803");
        valueOrderData.put("flight_number","CA88");
        valueOrderData.put("takeoff_time","2022-09-17");
        valueOrderData.put("expired","1");
        db.insert("My_Order",null,valueOrderData);
    }

    //添加航班数据函数
    public void addFlightData(SQLiteDatabase db){
        ContentValues valueFlightData = new ContentValues();

        valueFlightData.put("flight_number","CA88");
        valueFlightData.put("is_domestic","1"); //1为国内，0为国外
        valueFlightData.put("takeoff_city","chengdu");
        valueFlightData.put("landing_city","shanghai");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("price","500");
        valueFlightData.put("takeoff_time","2022-09-17");
        valueFlightData.put("shipping_space","009-A");
        valueFlightData.put("punctuality_rate","0.99");
        valueFlightData.put("is_direct_flight","1");    //1为直飞，0为中转
        valueFlightData.put("is_share","1");    //1为共享，0为不共享
        valueFlightData.put("time_period","3h");
        valueFlightData.put("airline_company","hangkong");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","test1");
        valueFlightData.put("landing_terminal","test2");
        db.insert("Flight",null,valueFlightData);
    }

    //添加城市数据
    public void addCityData(SQLiteDatabase db){
        ContentValues valueCityData = new ContentValues();

        valueCityData.put("city_name","chengdu");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","shanghai");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","null");
        db.insert("City",null,valueCityData);
    }

    //添加航空公司数据
    public void addCompanyData(SQLiteDatabase db){
        ContentValues valueCompanyData = new ContentValues();

        valueCompanyData.put("company_name","hangkong");
        db.insert("Airline_Company",null,valueCompanyData);
    }
}