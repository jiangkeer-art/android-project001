package com.sapphireStar.android_project.MineActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.sapphireStar.android_project.After_Sign_Activity.FunctionActivity;
import com.sapphireStar.android_project.BeginActivity.MainActivity;
import com.sapphireStar.android_project.R;
import com.sapphireStar.dao.NormalUserDao;
import com.sapphireStar.dao.impl.NormalUserDaoImpl;

import java.sql.SQLException;

public class Change_Password extends AppCompatActivity {

    public String phone="",ID="",adm="";
    public EditText password1,password2,password3;
    public Button confirm;
    public String oldPassword="",newPassword="",reNewPassword="";
    private ImageButton back_to_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加点击事件监听
        setContentView(R.layout.activity_change_password);
        back_to_mine = findViewById(R.id.back_to_mine);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        password3 = findViewById(R.id.password3);
        confirm = findViewById(R.id.confirm);
        phone = getIntent().getStringExtra("phone");
        ID = getIntent().getStringExtra("id");
        adm = getIntent().getStringExtra("adm");
        OnClick onClick = new OnClick();
        confirm.setOnClickListener(onClick);
        back_to_mine.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {

        @SuppressLint("Range")
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.back_to_mine:
                    //返回按钮
                    intent = new Intent(Change_Password.this, FunctionActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("id",ID);
                    intent.putExtra("adm",adm);
                    intent.putExtra("frag",1);
                    startActivity(intent);
                    break;
                case R.id.confirm:
                    //修改密码
                    oldPassword = password1.getText().toString();
                    newPassword = password2.getText().toString();
                    reNewPassword = password3.getText().toString();
                    NormalUserDao normalUserDao = new NormalUserDaoImpl();
                    try {
                        normalUserDao.modifyPassword(phone,newPassword,reNewPassword,oldPassword);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    intent = new Intent(Change_Password.this, MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}