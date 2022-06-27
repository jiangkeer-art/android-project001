package com.sapphireStar.android_project.DataBase;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlHelper {
    private static final String CLS = "com.mysql.jdbc.Driver";
    private static final String URL ="jdbc:mysql://rm-bp1x97blp036xip86bo.mysql.rds.aliyuncs.com:3306/sapphire_star?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true";
    private static final String USER ="guanosine";
    private static final String PASSWORD ="123456s!";
    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet cursor;
    public static void getDatabase(){
        try{
            Class.forName(CLS);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            statement=connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    };
    public static void closeDatabase(){
        try {
            if(cursor != null){
                cursor.close();
            }
            if(statement != null){
                statement.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

