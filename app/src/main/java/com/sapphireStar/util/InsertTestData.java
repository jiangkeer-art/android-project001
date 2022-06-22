package com.sapphireStar.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class InsertTestData {
    public InsertTestData(SQLiteDatabase db){
        addUserData(db);
        addCityData(db);
        addCompanyData(db);
        addFlightData(db);
        addAdministratorsUserData(db);
        addNormalUserData(db);
        addPlaneTicketData(db);
        addOrderData(db);
        addMyAttentionData(db);
    }
    //添加关注相关数据
    public void addMyAttentionData(SQLiteDatabase db){
        ContentValues valueMyAttentionData = new ContentValues();

        valueMyAttentionData.put("plane_ticket_number","255");
        valueMyAttentionData.put("phone","13646245963");
        db.insert("My_Attention",null,valueMyAttentionData);

        valueMyAttentionData.put("plane_ticket_number","10086");
        valueMyAttentionData.put("phone","13223062510");
        db.insert("My_Attention",null,valueMyAttentionData);

        valueMyAttentionData.put("plane_ticket_number","12138");
        valueMyAttentionData.put("phone","14485357663");
        db.insert("My_Attention",null,valueMyAttentionData);


    }

    //添加机票数据
    public void addPlaneTicketData(SQLiteDatabase db){
        ContentValues valuePlaneTicketData = new ContentValues();

        valuePlaneTicketData.put("plane_ticket_number","255");
        valuePlaneTicketData.put("flight_number","0006");
        valuePlaneTicketData.put("takeoff_time","2022-7-25 16:30:00");
        valuePlaneTicketData.put("price","300");
        valuePlaneTicketData.put("shipping_space","经济舱");
        valuePlaneTicketData.put("state","1");
        db.insert("Plane_Ticket",null,valuePlaneTicketData);

        valuePlaneTicketData.put("plane_ticket_number","4008");
        valuePlaneTicketData.put("flight_number","0008");
        valuePlaneTicketData.put("takeoff_time","2022-7-30 20:30:00");
        valuePlaneTicketData.put("price","200");
        valuePlaneTicketData.put("shipping_space","经济舱");
        valuePlaneTicketData.put("state","1");
        db.insert("Plane_Ticket",null,valuePlaneTicketData);

        valuePlaneTicketData.put("plane_ticket_number","5835");
        valuePlaneTicketData.put("flight_number","0001");
        valuePlaneTicketData.put("takeoff_time","2022-8-5 00:01:00");
        valuePlaneTicketData.put("price","560");
        valuePlaneTicketData.put("shipping_space","头等舱");
        valuePlaneTicketData.put("state","1");
        db.insert("Plane_Ticket",null,valuePlaneTicketData);

        valuePlaneTicketData.put("plane_ticket_number","10086");
        valuePlaneTicketData.put("flight_number","0004");
        valuePlaneTicketData.put("takeoff_time","2022-7-18 19:00:00");
        valuePlaneTicketData.put("price","500");
        valuePlaneTicketData.put("shipping_space","头等舱");
        valuePlaneTicketData.put("state","0");
        db.insert("Plane_Ticket",null,valuePlaneTicketData);

        valuePlaneTicketData.put("plane_ticket_number","12138");
        valuePlaneTicketData.put("flight_number","0003");
        valuePlaneTicketData.put("takeoff_time","2022-7-16 21:00:00");
        valuePlaneTicketData.put("price","180");
        valuePlaneTicketData.put("shipping_space","经济舱");
        valuePlaneTicketData.put("state","0");
        db.insert("Plane_Ticket",null,valuePlaneTicketData);

    }

    //添加管理员用户数据函数
    public void addAdministratorsUserData(SQLiteDatabase db){
        ContentValues valueAdministrators_User =new ContentValues();

        valueAdministrators_User.put("phone","18222279903");
        valueAdministrators_User.put("id","Lord");
        db.insert("Administrators_User",null,valueAdministrators_User);

        valueAdministrators_User.put("phone","14758356742");
        valueAdministrators_User.put("id","sixflower");
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

        valueNormal_User.put("phone","13646245963");
        valueNormal_User.put("id","hanser");
        valueNormal_User.put("name","憨色儿");
        valueNormal_User.put("id_number","220284199205153026");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","18625103618");
        valueNormal_User.put("id","yousa");
        valueNormal_User.put("name","冷鸟");
        valueNormal_User.put("id_number","220284201812285620");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","18625893020");
        valueNormal_User.put("id","伊万酱打油");
        valueNormal_User.put("name","伊万");
        valueNormal_User.put("id_number","220223199903095020");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","15589725630");
        valueNormal_User.put("id","陆鳐lulu");
        valueNormal_User.put("name","陆鳐");
        valueNormal_User.put("id_number","220234199806143090");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","13223062510");
        valueNormal_User.put("id","张三");
        valueNormal_User.put("name","张三疯");
        valueNormal_User.put("id_number","220286198609199076");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","15566365525");
        valueNormal_User.put("id","白神遥haruka");
        valueNormal_User.put("name","白神兲");
        valueNormal_User.put("id_number","220246198611063029");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","15948657845");
        valueNormal_User.put("id","双料特工");
        valueNormal_User.put("name","穿山甲");
        valueNormal_User.put("id_number","220254198905258645");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","14485357663");
        valueNormal_User.put("id","rua猫");
        valueNormal_User.put("name","王一土");
        valueNormal_User.put("id_number","220266199507264206");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","18826124520");
        valueNormal_User.put("id","穆小泠official");
        valueNormal_User.put("name","穆小冷");
        valueNormal_User.put("id_number","220253199606170506");
        db.insert("Normal_User",null,valueNormal_User);

        valueNormal_User.put("phone","16633060633");
        valueNormal_User.put("id","好名都让狗起了");
        valueNormal_User.put("name","张弓长");
        valueNormal_User.put("id_number","220239199408162533");
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


        valueUser.put("phone","14758356742");
        valueUser.put("password","sixflower");
        valueUser.put("is_administrators","1");
        db.insert("User",null,valueUser);

        valueUser.put("phone","13646245963");
        valueUser.put("password","hanser255");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","18625103618");
        valueUser.put("password","yousa150");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","18625893020");
        valueUser.put("password","iiivan12138");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","15589725630");
        valueUser.put("password","luyao6166");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","13223062510");
        valueUser.put("password","abandon55");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","15566365525");
        valueUser.put("password","haruka5835");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","15948657845");
        valueUser.put("password","tnnd5678");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","14485357663");
        valueUser.put("password","rua369");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","16633060633");
        valueUser.put("password","biu6666");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);

        valueUser.put("phone","18826124520");
        valueUser.put("password","ling1122");
        valueUser.put("is_administrators","0");
        db.insert("User",null,valueUser);
    }

    //添加订单数据函数
    public void addOrderData(SQLiteDatabase db){
        ContentValues valueOrderData = new ContentValues();

        valueOrderData.put("order_number","25535");
        valueOrderData.put("phone","15566365525");
        valueOrderData.put("plane_ticket_number","5835");
        valueOrderData.put("state","1");
        db.insert("My_Order",null,valueOrderData);

        valueOrderData.put("order_number","114514");
        valueOrderData.put("phone","14485357663");
        valueOrderData.put("plane_ticket_number","12138");
        valueOrderData.put("state","0");
        db.insert("My_Order",null,valueOrderData);
    }

    //添加航班数据函数
    public void addFlightData(SQLiteDatabase db){
        ContentValues valueFlightData = new ContentValues();

        valueFlightData.put("flight_number","0001");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","大连");
        valueFlightData.put("landing_city","鸡西");
        valueFlightData.put("transit_city","长春");
        valueFlightData.put("takeoff_time","2022-8-5 00:01:00");
        valueFlightData.put("punctuality_rate","100.0");
        valueFlightData.put("is_direct_flight","1");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","60");//以分钟为单位
        valueFlightData.put("airline_company","中国国航");
        valueFlightData.put("food","1");//1为提供食物，0为不提供食物
        valueFlightData.put("departure_terminal","大连金州湾国际机场");
        valueFlightData.put("landing_terminal","鸡西兴凯湖机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0002");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","成都");
        valueFlightData.put("landing_city","北京");
        valueFlightData.put("transit_city","重庆");
        valueFlightData.put("takeoff_time","2022-7-15 01:00:00");
        valueFlightData.put("punctuality_rate","99.0");
        valueFlightData.put("is_direct_flight","1");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","80");//以分钟为单位
        valueFlightData.put("airline_company","四川航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","成都天府国际机场");
        valueFlightData.put("landing_terminal","北京首都国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0003");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","西安");
        valueFlightData.put("landing_city","大连");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022-7-16 21:00:00");
        valueFlightData.put("punctuality_rate","100.0");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","60");//以分钟为单位
        valueFlightData.put("airline_company","南方航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","西安咸阳国际机场");
        valueFlightData.put("landing_terminal","大连金州湾国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0004");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","长春");
        valueFlightData.put("landing_city","石家庄");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022-7-18 19:00:00");
        valueFlightData.put("punctuality_rate","99.8");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","90");//以分钟为单位
        valueFlightData.put("airline_company","吉祥航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","长春龙嘉国际机场");
        valueFlightData.put("landing_terminal","石家庄正定国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0005");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","北京");
        valueFlightData.put("landing_city","成都");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022-7-20 22:00:00");
        valueFlightData.put("punctuality_rate","100.0");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","66");//以分钟为单位
        valueFlightData.put("airline_company","春秋航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","北京首都国际机场");
        valueFlightData.put("landing_terminal","成都天府国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0006");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","石家庄");
        valueFlightData.put("landing_city","重庆");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022-7-25 16:30:00");
        valueFlightData.put("punctuality_rate","98.0");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","88");//以分钟为单位
        valueFlightData.put("airline_company","中国国航");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","石家庄正定国际机场");
        valueFlightData.put("landing_terminal","重庆江北国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0007");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","北京");
        valueFlightData.put("landing_city","长春");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022-7-28 02:00:00");
        valueFlightData.put("punctuality_rate","100.0");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","50");//以分钟为单位
        valueFlightData.put("airline_company","吉祥航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","北京首都国际机场");
        valueFlightData.put("landing_terminal","长春龙嘉国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0008");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","长春");
        valueFlightData.put("landing_city","天津");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022-7-30 20:30:00");
        valueFlightData.put("punctuality_rate","98.9");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","45");//以分钟为单位
        valueFlightData.put("airline_company","长荣航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","长春龙嘉国际机场");
        valueFlightData.put("landing_terminal","天津滨海国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0009");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","天津");
        valueFlightData.put("landing_city","重庆");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022-7-16 21:30:00");
        valueFlightData.put("punctuality_rate","100.0");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","70");//以分钟为单位
        valueFlightData.put("airline_company","吉祥航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","天津滨海国际机场");
        valueFlightData.put("landing_terminal","重庆江北国际机场");
        db.insert("Flight",null,valueFlightData);

        valueFlightData.put("flight_number","0010");
        valueFlightData.put("is_domestic","0"); //1为国外，0为国内
        valueFlightData.put("takeoff_city","重庆");
        valueFlightData.put("landing_city","鸡西");
        valueFlightData.put("transit_city","null");
        valueFlightData.put("takeoff_time","2022 -7-18 18:30:00");
        valueFlightData.put("punctuality_rate","100");
        valueFlightData.put("is_direct_flight","0");//1为需要中转，0为直飞
        valueFlightData.put("is_share","0");//0为不共享，1为共享
        valueFlightData.put("time_period","120");//以分钟为单位
        valueFlightData.put("airline_company","春秋航空");
        valueFlightData.put("food","1");
        valueFlightData.put("departure_terminal","重庆江北国际机场");
        valueFlightData.put("landing_terminal","鸡西兴凯湖机场");

        db.insert("Flight",null,valueFlightData);

    }

    //添加城市数据
    public void addCityData(SQLiteDatabase db){
        ContentValues valueCityData = new ContentValues();

        valueCityData.put("city_name","null");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","大连");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","鸡西");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","成都");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","长春");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","北京");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","天津");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","石家庄");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","西安");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","重庆");
        db.insert("City",null,valueCityData);

        valueCityData.put("city_name","贵阳");
        db.insert("City",null,valueCityData);
    }

    //添加航空公司数据
    public void addCompanyData(SQLiteDatabase db){
        ContentValues valueCompanyData = new ContentValues();

        valueCompanyData.put("company_name","中国国航");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","南方航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","东方航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","海南航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","长荣航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","深圳航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","吉祥航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","春秋航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","四川航空");
        db.insert("Airline_Company",null,valueCompanyData);

        valueCompanyData.put("company_name","中华航空");
        db.insert("Airline_Company",null,valueCompanyData);
    }
}
