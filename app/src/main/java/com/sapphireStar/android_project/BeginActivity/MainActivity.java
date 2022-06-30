package com.sapphireStar.android_project.BeginActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.android_project.Register.RegisterActivity;
import com.sapphireStar.android_project.VideoBackground.VideoViewBackground;
import com.sapphireStar.dao.AdministratorDao;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.AdministratorDaoImpl;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;
import com.sapphireStar.entity.Administrator;
import com.sapphireStar.entity.NormalUser;
import com.sapphireStar.util.CommonDB;
import com.sapphireStar.util.InsertTestData;

import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    private VideoViewBackground videoview;
    private Button quick_register,forget_password,sing_in;
    public String password="",username="",id="";
    public int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //创建数据库并向其中添加数据
        //CommonDB db = new CommonDB();
        //SQLiteDatabase sqlite = db.getSqliteObject(MainActivity.this,"FlightDataBase.db");
//        int version = db.getVersionControl();
//        //Toast.makeText(MainActivity.this, String.valueOf(version), Toast.LENGTH_SHORT).show();
//        if(version==1){
//            new InsertTestData(sqlite);
//            //Toast.makeText(MainActivity.this, version, Toast.LENGTH_SHORT).show();
//        }

        //对布局界面按钮添加监听事件
        quick_register = findViewById(R.id.quick_register);
        forget_password = findViewById(R.id.forget_password);
        sing_in = findViewById(R.id.sing_in);
        OnClick onClick = new OnClick();
        quick_register.setOnClickListener(onClick);
        forget_password.setOnClickListener(onClick);
        sing_in.setOnClickListener(onClick);

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
    private class OnClick implements View.OnClickListener{

        @SuppressLint("Range")
        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()){
                case R.id.quick_register:
                    //点击注册按钮
                    intent=new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.forget_password:break;

                case R.id.sing_in:
                    //点击登录按钮
                    EditText editText1 = (EditText) findViewById(R.id.username);
                    EditText editText2 = (EditText) findViewById(R.id.password);
                    //获取用户名和密码，
                    username = editText1.getText().toString();
                    password = editText2.getText().toString();
                    //进入数据库进行查询
                    CommonDB db = new CommonDB();
                    SQLiteDatabase sqlite = db.getSqliteObject(MainActivity.this,"FlightDataBase.db");
                    AdministratorDao administratorDao = new AdministratorDaoImpl(sqlite);
                    NormalUserDao normalUserDao = new NormalUserDaoImpl(sqlite);
                    Object obj = null;
                    try {
                        obj = normalUserDao.Login(username,password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if(obj == null){
                        Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Sing in Succeeded", Toast.LENGTH_SHORT).show();
                        intent=new Intent(MainActivity.this, FunctionActivity.class);
                        intent.putExtra("phone",username);
                        if(obj.getClass().getSimpleName().equals("Administrator")){
                            Administrator user_info = null;
                            try {
                                user_info = administratorDao.getAdministratorByPhone(username);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            id = user_info.getId();
                            String is_administrator="1";
                            intent.putExtra("id",id);
                            intent.putExtra("Administrator",(Administrator)obj);
                            intent.putExtra("adm",is_administrator);

                        }
                        else {
                            NormalUser user_info = null;
                            try {
                                user_info = normalUserDao.getUserByPhone(username);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            id = user_info.getId();
                            String is_administrator="0";
                            intent.putExtra("id",id);
                            intent.putExtra("NormalUser",(NormalUser)obj);
                            intent.putExtra("administrator",is_administrator);
                        }
                        startActivity(intent);
                        break;
                    }
//                    Cursor cursor = sqlite.query("User",null,null,null,null,null,null);
//                    if(cursor.moveToFirst()){
//                        do{
//                            UserName = cursor.getString(cursor.getColumnIndex("phone"));
//                            if(UserName.equals(username)){
//                                Password = cursor.getString(cursor.getColumnIndex("password"));
//                                if(Password.equals(password)){
//                                    Toast.makeText(MainActivity.this, "Sing in Succeeded", Toast.LENGTH_SHORT).show();
//                                    intent=new Intent(MainActivity.this, FunctionActivity.class);
//                                    intent.putExtra("phone",UserName);
//                                    startActivity(intent);
//                                    a=1;
//                                    break;
//                                }
//                            }
//                        }while(cursor.moveToNext());
//                        if(a==0){
//                            Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    cursor.close();
                    break;
            }
        }
    }
}