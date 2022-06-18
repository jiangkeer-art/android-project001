package com.sapphireStar.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;
    private VideoViewBackground videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dbHelper = new DataBaseHelper(this,"FlightDataBase.dp",null,17);

        //创建数据库并向其中添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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


    }

    //添加机票数据
    public void addPlaneTicketData(SQLiteDatabase db){
        ContentValues valuePlaneTicketData = new ContentValues();


    }

    //添加管理员用户数据函数
    public void addAdministratorsUserData(SQLiteDatabase db){
        ContentValues valueAdministrators_User =new ContentValues();

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

    }

    //添加航班数据函数
    public void addFlightData(SQLiteDatabase db){
        ContentValues valueFlightData = new ContentValues();

    }

    //添加城市数据
    public void addCityData(SQLiteDatabase db){
        ContentValues valueCityData = new ContentValues();

    }

    //添加航空公司数据
    public void addCompanyData(SQLiteDatabase db){
        ContentValues valueCompanyData = new ContentValues();

    }

    private void initView(){
        //加载视频资源
        videoview = (VideoViewBackground) findViewById(R.id.videoView);
        //设置播放路径
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testtest));
        //播放
        videoview.start();
        //循环播放
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });
    }

    //返回重启加载
    @Override
    protected void onRestart() {
        initView();
        super.onRestart();
    }

    //防止锁屏或者切出的时候，音乐在播放
    @Override
    protected void onStop() {
        videoview.stopPlayback();
        super.onStop();
    }
}