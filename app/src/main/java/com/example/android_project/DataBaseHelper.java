package com.example.android_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER = "Create table User("
            + "phone varchar(255) primary key autoincrement,"
            + "password varchar(255),"
            + "is_administrators integer)";

    public static final String CREATE_NORMAL_USER = "Create table Normal_User("
            + "phone varchar(255) primary key autoincrement,"
            + "id varchar(255),"
            + "name varchar(255),"
            + "id_number varchar(255))";

    public static final String CREATE_ADMINISTRATORS_USER = "Create table Administrators_User("
            + "phone varchar(255) primary key autoincrement,"
            + "id varchar(255))";

    public static final String CREATE_MY_ORDER = "Create table My_Order("
            + "order_number varchar(255) primary key autoincrement,"
            + "phone varchar(255),"
            + "flight_number varchar(255),"
            + "takeoff_time varchar(255),"
            + "expired integer)";

    public static final String CREATE_FLIGHT = "Create table Flight("
            + "flight_number varchar(255) primary key autoincrement,"
            + "is_domestic integer,"
            + "takeoff_city varchar(255),"
            + "landing_city varchar(255),"
            + "transit_city varchar(255),"
            + "price integer,"
            + "takeoff_time varchar(255) primary key autoincrement,"
            + "shipping_space varchar(255),"
            + "punctuality_rate real,"
            + "is_direct_flight integer,"
            + "is_share integer,"
            + "time_period varchar(255),"
            + "airline_company varchar(255),"
            + "food varchar(255),"
            + "departure_terminal varchar(255),"
            + "landing_terminal varchar(255))";

    public static final String CREATE_CITY = "Create table City("
            + "city_number varchar(255) primary key autoincrement,"
            + "city_name varchar(255))";

    public static final String CREATE_AIRLINE_COMPANY = "Create table Airline_Company("
            + "company_number varchar(255) primary key autoincrement,"
            + "company_name varchar(255))";

    private Context mContext;
    public DataBaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
