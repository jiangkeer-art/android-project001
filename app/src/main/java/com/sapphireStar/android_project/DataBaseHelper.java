package com.sapphireStar.android_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    /*
    用户表：
    用户名（电话号码）（主键）
    密码
    是否为管理员
    */
    public static final String CREATE_USER = "Create table User("
            + "phone varchar(12) primary key not null,"
            + "password varchar(64),"
            + "is_administrators integer)";

    /*
    普通用户表：
    用户名（电话号码）（主键）
    昵称
    姓名
    身份证号
    外键用户名连接到用户表的用户名
     */
    public static final String CREATE_NORMAL_USER = "Create table Normal_User("
            + "phone varchar(12) primary key not null,"
            + "id varchar(16),"
            + "name varchar(5),"
            + "id_number varchar(24),"
            + "foreign key (phone) references User(phone))";

    /*
    管理员用户表
    用户名（电话号码）（主键）
    昵称
    外键用户名连接到用户表的用户名
     */
    public static final String CREATE_ADMINISTRATORS_USER = "Create table Administrators_User("
            + "phone varchar(12) primary key not null,"
            + "id varchar(16),"
            + "foreign key (phone) references User(phone))";

    /*
    订单表：
    订单编号（主键）
    用户名（电话号码）
    机票编号
    状态
    外键用户名连接到用户表的用户名
    外键航班编号连接到航班表的航班编号
    外键起飞时间连接到航班表的起飞时间
     */
    public static final String CREATE_MY_ORDER = "Create table My_Order("
            + "order_number integer primary key autoincrement,"
            + "phone varchar(12),"
            + "plane_ticket_number integer,"
            + "state varchar(15),"
            + "foreign key (phone) references User(phone),"
            + "foreign key (plane_ticket_number) references Plane_Ticket(plane_ticket_number))";

    /*
    关注表：
    机票编号（主键）
    用户名（主键）
     */
    public static final String CREATE_MY_ATTENTION = "Create table My_Attention("
            + "plane_ticket_number integer not null unique,"
            + "phone varchar(12) not null unique,"
            + "primary key (plane_ticket_number,phone),"
            + "foreign key (plane_ticket_number) references Plane_Ticket(plane_ticket_number),"
            + "foreign key (phone) references User(phone))";

    /*
    机票表：
    机票编号（主键）
    航班号
    起飞时间
    价格
    舱位
    状态
     */
    public static final String CREATE_PLANE_TICKET = "Create table Plane_Ticket("
            + "plane_ticket_number integer primary key not null,"
            + "flight_number varchar(10),"
            + "takeoff_time datetime,"
            + "price integer,"
            + "shipping_space varchar(15),"
            + "state varchar(15),"
            + "foreign key (flight_number) references Flight(flight_number),"
            + "foreign key (takeoff_time) references Flight(takeoff_time))";

    /*
    航班表：
    航班编号
    国内/国际
    起飞城市
    降落城市
    中专城市
    起飞时间
    准点率
    是否直飞
    是否共享
    时间段
    航空公司
    餐食
    起飞航站楼
    降落航站楼
    主键：航班编号，起飞时间
    外键起飞城市连接到城市表的城市名
    外键降落城市连接到城市表的城市名
    外键中转城市连接到城市表的城市名
    外键航空公司连接到航空公司表的公司名
     */
    public static final String CREATE_FLIGHT = "Create table Flight("
            + "flight_number varchar(10) not null unique,"
            + "is_domestic integer,"
            + "takeoff_city varchar(15),"
            + "landing_city varchar(15),"
            + "transit_city varchar(15),"
            + "takeoff_time datetime not null unique,"
            + "punctuality_rate real,"
            + "is_direct_flight integer,"
            + "is_share integer,"
            + "time_period varchar(8),"
            + "airline_company varchar(15),"
            + "food integer,"
            + "departure_terminal varchar(15),"
            + "landing_terminal varchar(15),"
            + "primary key (flight_number,takeoff_time),"
            + "constraint fk_takeoff_city foreign key (takeoff_city) references City(city_name),"
            + "constraint fk_landing_city foreign key (landing_city) references City(city_name),"
            + "constraint fk_transit_city foreign key (transit_city) references City(city_name),"
            + "constraint fk_airline_company foreign key (airline_company) references Airline_Company(company_name))";

    /*
    城市表：
    城市编号（主键）
    城市名
     */
    public static final String CREATE_CITY = "Create table City("
            + "city_number integer primary key autoincrement,"
            + "city_name varchar(15) not null unique)";

    /*
    航空公司表：
    公司编号（主键）
    公司名
     */
    public static final String CREATE_AIRLINE_COMPANY = "Create table Airline_Company("
            + "company_number integer primary key autoincrement,"
            + "company_name varchar(15) not null unique)";

    private Context mContext;
    public DataBaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    //打开SQLite的外键功能
    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建各个表
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_NORMAL_USER);
        sqLiteDatabase.execSQL(CREATE_ADMINISTRATORS_USER);
        sqLiteDatabase.execSQL(CREATE_MY_ORDER);
        sqLiteDatabase.execSQL(CREATE_FLIGHT);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_AIRLINE_COMPANY);
        sqLiteDatabase.execSQL(CREATE_MY_ATTENTION);
        sqLiteDatabase.execSQL(CREATE_PLANE_TICKET);
        //创建成功时发出成功消息
        Toast.makeText(mContext, "DataBase Create Succeeded", Toast.LENGTH_SHORT).show();
    }

    //数据库更新时的代码
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists User");
        sqLiteDatabase.execSQL("drop table if exists Normal_User");
        sqLiteDatabase.execSQL("drop table if exists My_Order");
        sqLiteDatabase.execSQL("drop table if exists Flight");
        sqLiteDatabase.execSQL("drop table if exists City");
        sqLiteDatabase.execSQL("drop table if exists Airline_Company");
        sqLiteDatabase.execSQL("drop table if exists Administrators_User");
        sqLiteDatabase.execSQL("drop table if exists My_Attention");
        sqLiteDatabase.execSQL("drop table if exists Plane_Ticket");
        onCreate(sqLiteDatabase);
    }
}
