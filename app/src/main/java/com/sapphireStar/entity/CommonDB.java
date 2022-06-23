package com.sapphireStar.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.DataBase.DataBaseHelper;

public class CommonDB {
    private int versionControl;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase sqlite;
    public SQLiteDatabase getSqliteObject(Context context,String db_name){
        dbHelper = new DataBaseHelper(context,db_name,null,24);
        sqlite = dbHelper.getWritableDatabase();
        versionControl=dbHelper.versionControl;
        return sqlite;
    }
    public int getVersionControl(){
        return versionControl;
    }
}
