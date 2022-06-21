package com.sapphireStar.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sapphireStar.dao.FlightDao;
import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.dao.MyOrderDao;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.PlaneTicketDao;
import com.sapphireStar.dao.impl.FlightDaoImpl;
import com.sapphireStar.dao.impl.MyAttentionDaoImpl;
import com.sapphireStar.dao.impl.MyOrderDaoImpl;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;
import com.sapphireStar.dao.impl.PlaneTicketDaoImpl;
import com.sapphireStar.entity.Flight;
import com.sapphireStar.entity.MyAttention;
import com.sapphireStar.entity.MyOrder;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.entity.PlaneTicket;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;
    private VideoViewBackground videoview;
    private Button quick_register,forget_password,sing_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dbHelper = new DataBaseHelper(this,"FlightDataBase.db",null,18);

        //创建数据库并向其中添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //new InsertTestData(db);

        quick_register = findViewById(R.id.quick_register);
        forget_password = findViewById(R.id.forget_password);
        sing_in = findViewById(R.id.sing_in);
        OnClick onClick = new OnClick();
        quick_register.setOnClickListener(onClick);
        forget_password.setOnClickListener(onClick);
        sing_in.setOnClickListener(onClick);

//        PlaneTicketDao planeTicketDao =new PlaneTicketDaoImpl(dbHelper);
//        List<PlaneTicket> list = planeTicketDao.getPlaneTicketByFlight("0004");
//        for(int i = 0; i < list.size();i++){
//            Log.d("testDAO", list.get(i).toString());
//        }
        NormalUserDao normalUserDao = new NormalUserDaoImpl(dbHelper);
        Log.d("testDAO", normalUserDao.getUserByPhone("15589725630").toString());


    }
    private void initView(){
        //加载视频资源
        videoview = (VideoViewBackground) findViewById(R.id.videoView);
        //设置播放路径
        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wy1));
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

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()){
                case R.id.quick_register:
                    intent=new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.forget_password:

                case R.id.sing_in:

            }
        }
    }

}