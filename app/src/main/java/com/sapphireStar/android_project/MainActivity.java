package com.sapphireStar.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sapphireStar.dao.MyAttentionDao;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.MyAttentionDaoImpl;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;
import com.sapphireStar.entity.MyAttention;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.util.InsertTestData;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBaseHelper dbHelper;
    private VideoViewBackground videoview;
    private Button quick_register,forget_password,sing_in;
    public String password="",username="",Password="",UserName="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        dbHelper = new DataBaseHelper(this,"FlightDataBase.db",null,20);

        //创建数据库并向其中添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(dbHelper.versionControl==1){
            new InsertTestData(db);
            //Toast.makeText(MainActivity.this, "Data Insert Succeeded", Toast.LENGTH_SHORT).show();
        }

        //对布局界面按钮添加监听事件
        quick_register = findViewById(R.id.quick_register);
        forget_password = findViewById(R.id.forget_password);
        sing_in = findViewById(R.id.sing_in);
        OnClick onClick = new OnClick();
        quick_register.setOnClickListener(onClick);
        forget_password.setOnClickListener(onClick);
        sing_in.setOnClickListener(onClick);

        MyAttentionDao myAttentionDao=new MyAttentionDaoImpl(db,dbHelper);
        List<MyAttention> list = myAttentionDao.getMyAttention("13646245963");

        Log.d("TestDAO", list.get(0).toString());

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

    //button点击事件
    class OnClick implements View.OnClickListener{

        @SuppressLint("Range")
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
                    EditText editText1 = (EditText) findViewById(R.id.username);
                    EditText editText2 = (EditText) findViewById(R.id.password);
                    username = editText1.getText().toString();
                    password = editText2.getText().toString();
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = db.query("User",null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            UserName = cursor.getString(cursor.getColumnIndex("phone"));
                            if(UserName.equals(username)){
                                Password = cursor.getString(cursor.getColumnIndex("password"));
                                if(Password.equals(password)){
                                    Toast.makeText(MainActivity.this, "Sing in Succeeded", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }while(cursor.moveToNext());
                        Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    break;
            }
        }
    }

}