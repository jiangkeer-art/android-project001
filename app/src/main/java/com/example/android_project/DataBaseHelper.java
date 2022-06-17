package com.example.android_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "Create table User("
            + "phone varchar(255) primary key,"
            + "password varchar(255),"
            + "is_administrators integer)";

    public static final String CREATE_NORMAL_USER = "Create table Normal_User("
            + "phone varchar(255) primary key,"
            + "id varchar(255),"
            + "name varchar(255),"
            + "id_number varchar(255),"
            + "foreign key (phone) references User(phone))";

    public static final String CREATE_ADMINISTRATORS_USER = "Create table Administrators_User("
            + "phone varchar(255) primary key,"
            + "id varchar(255),"
            + "foreign key (phone) references User(phone))";

    public static final String CREATE_MY_ORDER = "Create table My_Order("
            + "order_number integer primary key autoincrement,"
            + "phone varchar(255),"
            + "flight_number varchar(255),"
            + "takeoff_time varchar(255),"
            + "expired integer,"
            + "foreign key (phone) references User(phone),"
            + "foreign key (flight_number) references Flight(flight_number),"
            + "foreign key (takeoff_time) references Flight(takeoff_time))";

    public static final String CREATE_FLIGHT = "Create table Flight("
            + "flight_number varchar(255),"
            + "is_domestic integer,"
            + "takeoff_city varchar(255),"
            + "landing_city varchar(255),"
            + "transit_city varchar(255),"
            + "price integer,"
            + "takeoff_time varchar(255),"
            + "shipping_space varchar(255),"
            + "punctuality_rate real,"
            + "is_direct_flight integer,"
            + "is_share integer,"
            + "time_period varchar(255),"
            + "airline_company varchar(255),"
            + "food varchar(255),"
            + "departure_terminal varchar(255),"
            + "landing_terminal varchar(255),"
            + "primary key (flight_number,takeoff_time),"
            + "foreign key (takeoff_city) references City(city_name),"
            + "foreign key (landing_city) references City(city_name),"
            + "foreign key (transit_city) references City(city_name),"
            + "foreign key (airline_company) references Airline_Company(company_name))";

    public static final String CREATE_CITY = "Create table City("
            + "city_number integer primary key autoincrement,"
            + "city_name varchar(255))";

    public static final String CREATE_AIRLINE_COMPANY = "Create table Airline_Company("
            + "company_number integer primary key autoincrement,"
            + "company_name varchar(255))";

    private Context mContext;
    public DataBaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_NORMAL_USER);
        sqLiteDatabase.execSQL(CREATE_ADMINISTRATORS_USER);
        sqLiteDatabase.execSQL(CREATE_MY_ORDER);
        sqLiteDatabase.execSQL(CREATE_FLIGHT);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_AIRLINE_COMPANY);
        Toast.makeText(mContext, "DataBase Create Succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists User");
        sqLiteDatabase.execSQL("drop table if exists Normal_User");
        sqLiteDatabase.execSQL("drop table if exists My_Order");
        sqLiteDatabase.execSQL("drop table if exists Flight");
        sqLiteDatabase.execSQL("drop table if exists City");
        sqLiteDatabase.execSQL("drop table if exists Airline_Company");
        sqLiteDatabase.execSQL("drop table if exists Administrators_User");
        onCreate(sqLiteDatabase);
    }
}
